<template>
  <div class="dashboard-page">
    <div class="hero-card">
      <div class="hero-copy">
        <div class="hero-tag">管理控制台</div>
        <div class="hero-title">欢迎使用 {{ this.$project.projectName }}</div>
        <div class="hero-desc">
          后台首页展示本月订单概览、近 7 日销售趋势和商品排行榜，页面数据会随当前时间自动切换到对应月份。
        </div>
      </div>
      <div class="hero-meta">
        <div class="meta-block">
          <div class="meta-label">当前角色</div>
          <div class="meta-value">{{ roleName || '管理员' }}</div>
        </div>
        <div class="meta-block">
          <div class="meta-label">登录账号</div>
          <div class="meta-value">{{ username || 'admin' }}</div>
        </div>
      </div>
    </div>

    <div class="summary-grid">
      <div class="summary-card accent-blue">
        <div class="summary-label">{{ currentMonthLabel }}订单数</div>
        <div class="summary-value">{{ summary.monthOrderCount }}</div>
      </div>
      <div class="summary-card accent-green">
        <div class="summary-label">{{ currentMonthLabel }}已完成订单</div>
        <div class="summary-value">{{ summary.monthFinishedCount }}</div>
      </div>
      <div class="summary-card accent-gold">
        <div class="summary-label">{{ currentMonthLabel }}退款订单</div>
        <div class="summary-value">{{ summary.monthRefundCount }}</div>
      </div>
      <div class="summary-card accent-red">
        <div class="summary-label">{{ currentMonthLabel }}销售额</div>
        <div class="summary-value">￥{{ summary.monthAmount }}</div>
      </div>
    </div>

    <div class="chart-grid">
      <div class="chart-card">
        <div class="chart-title">近 7 日订单销量趋势</div>
        <div id="home-trend-quantity-chart" class="chart-box"></div>
      </div>
      <div class="chart-card">
        <div class="chart-title">近 7 日订单销额趋势</div>
        <div id="home-trend-amount-chart" class="chart-box"></div>
      </div>
      <div class="chart-card">
        <div class="chart-title">{{ currentMonthLabel }}商品销量 TOP5</div>
        <div id="home-rank-quantity-chart" class="chart-box"></div>
      </div>
      <div class="chart-card">
        <div class="chart-title">{{ currentMonthLabel }}商品销额 TOP5</div>
        <div id="home-rank-amount-chart" class="chart-box"></div>
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router/router-static'
import * as echarts from 'echarts'

export default {
  data() {
    return {
      username: '',
      roleName: '',
      currentMonth: '',
      summary: {
        monthOrderCount: 0,
        monthFinishedCount: 0,
        monthRefundCount: 0,
        monthAmount: '0.00'
      },
      charts: {
        trendQuantity: null,
        trendAmount: null,
        rankQuantity: null,
        rankAmount: null
      }
    }
  },
  computed: {
    currentMonthLabel() {
      if (!this.currentMonth) {
        return '本月'
      }
      const parts = this.currentMonth.split('-')
      return `${parts[0]}年${Number(parts[1])}月`
    }
  },
  created() {
    this.init()
  },
  mounted() {
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
    Object.keys(this.charts).forEach((key) => {
      if (this.charts[key]) {
        this.charts[key].dispose()
        this.charts[key] = null
      }
    })
  },
  methods: {
    init() {
      if (this.$storage.get('Token')) {
        this.$http({
          url: `${this.$storage.get('sessionTable')}/session`,
          method: 'get'
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.username = data.data.username || data.data.yonghuzhanghao || ''
            this.roleName = data.data.role || '管理员'
            this.$nextTick(() => {
              this.loadDashboard()
            })
          } else {
            router.push({ name: 'login' })
          }
        })
      } else {
        router.push({ name: 'login' })
      }
    },
    loadDashboard() {
      this.$http({
        url: 'orders/dashboard',
        method: 'get'
      }).then(({ data }) => {
        if (data && data.code === 0) {
          const dashboard = data.data || {}
          const summary = dashboard.summary || {}
          this.currentMonth = dashboard.currentMonth || ''
          this.summary.monthOrderCount = summary.monthOrderCount || 0
          this.summary.monthFinishedCount = summary.monthFinishedCount || 0
          this.summary.monthRefundCount = summary.monthRefundCount || 0
          this.summary.monthAmount = summary.monthAmount !== undefined ? Number(summary.monthAmount).toFixed(2) : '0.00'
          this.renderTrendCharts(dashboard.trend || [])
          this.renderRankChart(
            'home-rank-quantity-chart',
            'rankQuantity',
            dashboard.quantityRank || [],
            'quantityTotal',
            '#5b8ff9'
          )
          this.renderRankChart(
            'home-rank-amount-chart',
            'rankAmount',
            dashboard.amountRank || [],
            'amountTotal',
            '#f6bd16'
          )
        }
      })
    },
    renderTrendCharts(rows) {
      const recentDates = this.getRecentDates(7)
      const trendMap = {}
      rows.forEach(item => {
        trendMap[item.statDate] = {
          quantityTotal: Number(item.quantityTotal || 0),
          amountTotal: Number(item.amountTotal || 0)
        }
      })

      const xAxis = recentDates
      const quantityData = recentDates.map(date => {
        return trendMap[date] ? trendMap[date].quantityTotal : 0
      })
      const amountData = recentDates.map(date => {
        return trendMap[date] ? Number(trendMap[date].amountTotal).toFixed(2) : 0
      })

      this.renderLineChart(
        'home-trend-quantity-chart',
        'trendQuantity',
        xAxis,
        quantityData,
        '#5b8ff9'
      )
      this.renderLineChart(
        'home-trend-amount-chart',
        'trendAmount',
        xAxis,
        amountData,
        '#5ad8a6'
      )
    },
    getRecentDates(days) {
      const list = []
      const today = new Date()
      for (let i = days - 1; i >= 0; i--) {
        const d = new Date(today)
        d.setDate(today.getDate() - i)
        list.push(this.formatDateKey(d))
      }
      return list
    },
    formatDateKey(date) {
      const year = date.getFullYear()
      const month = `${date.getMonth() + 1}`.padStart(2, '0')
      const day = `${date.getDate()}`.padStart(2, '0')
      return `${year}-${month}-${day}`
    },
    renderLineChart(id, key, xAxis, yAxis, color) {
      if (!this.charts[key]) {
        this.charts[key] = echarts.init(document.getElementById(id), 'macarons')
      }
      this.charts[key].setOption({
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '4%',
          right: '4%',
          bottom: '8%',
          top: '14%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: xAxis
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: yAxis,
            type: 'line',
            smooth: true,
            symbolSize: 8,
            itemStyle: {
              color: color
            },
            lineStyle: {
              color: color,
              width: 3
            },
            areaStyle: {
              color: color,
              opacity: 0.12
            }
          }
        ]
      })
    },
    renderRankChart(id, key, rows, valueKey, color) {
      if (!this.charts[key]) {
        this.charts[key] = echarts.init(document.getElementById(id), 'macarons')
      }
      const xAxis = rows.map(item => item.goodname)
      const yAxis = rows.map(item => Number(item[valueKey] || 0))
      this.charts[key].setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '4%',
          right: '4%',
          bottom: '18%',
          top: '14%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: xAxis,
          axisLabel: {
            interval: 0,
            rotate: 20
          }
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: yAxis,
            type: 'bar',
            itemStyle: {
              color: color,
              borderRadius: [8, 8, 0, 0]
            }
          }
        ]
      })
    },
    handleResize() {
      Object.keys(this.charts).forEach((key) => {
        if (this.charts[key]) {
          this.charts[key].resize()
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-page {
  min-height: 100%;
  padding: 24px;
}

.hero-card {
  display: flex;
  justify-content: space-between;
  align-items: stretch;
  gap: 20px;
  padding: 28px 30px;
  border-radius: 28px;
  background: linear-gradient(135deg, rgba(255, 255, 255, 0.98), rgba(235, 244, 255, 0.92));
  box-shadow: 0 12px 24px rgba(90, 120, 160, 0.12);
}

.hero-copy {
  flex: 1;
}

.hero-tag {
  display: inline-block;
  margin-bottom: 14px;
  padding: 6px 16px;
  border-radius: 999px;
  background: rgba(106, 162, 237, 0.14);
  color: #4d8fd8;
  font-size: 13px;
  font-weight: 600;
}

.hero-title {
  margin-bottom: 12px;
  color: #2b3440;
  font-size: 34px;
  font-weight: 700;
  line-height: 1.25;
}

.hero-desc {
  max-width: 760px;
  color: #637083;
  font-size: 15px;
  line-height: 1.8;
}

.hero-meta {
  display: flex;
  gap: 14px;
  align-self: flex-start;
}

.meta-block {
  min-width: 140px;
  padding: 16px 18px;
  border-radius: 20px;
  background: rgba(245, 248, 252, 0.95);
}

.meta-label {
  margin-bottom: 8px;
  color: #7d8da2;
  font-size: 13px;
}

.meta-value {
  color: #2d3c4f;
  font-size: 26px;
  font-weight: 700;
}

.summary-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 18px;
  margin-top: 22px;
}

.summary-card {
  padding: 22px 24px;
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.96);
  box-shadow: 0 10px 20px rgba(90, 120, 160, 0.1);
}

.accent-blue {
  border-top: 5px solid #5b8ff9;
}

.accent-green {
  border-top: 5px solid #5ad8a6;
}

.accent-gold {
  border-top: 5px solid #f6bd16;
}

.accent-red {
  border-top: 5px solid #e8684a;
}

.summary-label {
  margin-bottom: 12px;
  color: #7b8a9d;
  font-size: 14px;
}

.summary-value {
  color: #273444;
  font-size: 32px;
  font-weight: 700;
}

.chart-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 18px;
  margin-top: 22px;
}

.chart-card {
  padding: 18px 18px 10px;
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.97);
  box-shadow: 0 10px 20px rgba(90, 120, 160, 0.1);
}

.chart-title {
  margin: 4px 6px 12px;
  color: #2d3c4f;
  font-size: 16px;
  font-weight: 700;
}

.chart-box {
  width: 100%;
  height: 340px;
}

@media (max-width: 1200px) {
  .summary-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .hero-card {
    flex-direction: column;
  }

  .hero-meta {
    width: 100%;
    flex-wrap: wrap;
  }
}

@media (max-width: 900px) {
  .chart-grid,
  .summary-grid {
    grid-template-columns: 1fr;
  }

  .dashboard-page {
    padding: 16px;
  }

  .hero-title {
    font-size: 28px;
  }
}
</style>
