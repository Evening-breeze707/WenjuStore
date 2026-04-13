<template>
  <div class="home-showcase">
    <section class="carousel-panel">
      <div class="panel-head">
        <div>
          <p class="panel-tag">首页轮播</p>
          <h3>精选展示</h3>
        </div>
      </div>
      <el-carousel
        class="home-carousel"
        trigger="click"
        indicator-position="outside"
        arrow="always"
        height="460px"
        :autoplay="true"
        :interval="5000"
      >
        <el-carousel-item v-for="item in carouselList" :key="item.id">
          <el-image class="home-image" :src="baseUrl + item.value" fit="cover"></el-image>
        </el-carousel-item>
      </el-carousel>
    </section>

    <section class="product-panel">
      <div class="panel-head">
        <div>
          <p class="panel-tag">精选推荐</p>
          <h3>文具信息推荐</h3>
        </div>
        <button class="panel-link" @click="moreBtn('wenjuxinxi')">查看更多</button>
      </div>
      <div class="product-grid">
        <article
          v-for="item in wenjuxinxiRecommend.slice(0, 6)"
          :key="item.id"
          class="product-card"
          @click="toDetail('wenjuxinxiDetail', item)"
        >
          <div class="product-thumb">
            <img :src="frontImage(item.wenjutupian)" alt="">
          </div>
          <div class="product-meta">
            <p class="product-type">{{ item.wenjufenlei }}</p>
            <h4>{{ item.wenjumingcheng }}</h4>
            <div class="product-foot">
              <span>{{ item.wenjupinpai }}</span>
              <strong>¥{{ item.price }}</strong>
            </div>
          </div>
        </article>
      </div>
    </section>

    <section class="product-panel alt">
      <div class="panel-head">
        <div>
          <p class="panel-tag">拓展展示</p>
          <h3>娱乐商品推荐</h3>
        </div>
        <button class="panel-link" @click="moreBtn('yuleshangpin')">查看更多</button>
      </div>
      <div class="feature-split" v-if="yuleshangpinRecommend.length">
        <article class="feature-lead" @click="toDetail('yuleshangpinDetail', yuleshangpinRecommend[0])">
          <img :src="frontImage(yuleshangpinRecommend[0].shangpintupian)" alt="">
          <div class="feature-overlay">
            <p>{{ yuleshangpinRecommend[0].shangpinpinpai }}</p>
            <h4>{{ yuleshangpinRecommend[0].shangpinmingcheng }}</h4>
          </div>
        </article>
        <div class="feature-stack">
          <article
            v-for="item in yuleshangpinRecommend.slice(1, 5)"
            :key="item.id"
            class="mini-card"
            @click="toDetail('yuleshangpinDetail', item)"
          >
            <img :src="frontImage(item.shangpintupian)" alt="">
            <div>
              <p>{{ item.shangpinpinpai }}</p>
              <h4>{{ item.shangpinmingcheng }}</h4>
              <strong>¥{{ item.price }}</strong>
            </div>
          </article>
        </div>
      </div>
    </section>

    <section class="news-panel">
      <div class="panel-head">
        <div>
          <p class="panel-tag">最新动态</p>
          <h3>公告信息</h3>
        </div>
        <button class="panel-link" @click="moreBtn('news')">查看更多</button>
      </div>
      <div class="news-grid">
        <article
          v-for="item in newsList"
          :key="item.id"
          class="news-card"
          @click="toDetail('newsDetail', item)"
        >
          <img class="news-image" :src="baseUrl + item.picture" alt="">
          <div class="news-copy">
            <p class="news-date">{{ item.addtime ? item.addtime.split(' ')[0] : '' }}</p>
            <h4>{{ item.title }}</h4>
            <p class="news-intro">{{ item.introduction }}</p>
          </div>
        </article>
      </div>
    </section>

    <section class="story-panel">
      <div class="story-card story-combined">
        <div class="story-visual" v-if="aboutGallery.length">
          <el-carousel
            class="story-carousel"
            trigger="click"
            indicator-position="outside"
            arrow="always"
            height="360px"
            :autoplay="true"
            :interval="5000"
          >
            <el-carousel-item v-for="(item, index) in aboutGallery" :key="index">
              <img class="story-image" :src="baseUrl + item" alt="">
            </el-carousel-item>
          </el-carousel>
        </div>

        <div class="story-copy">
          <p class="panel-tag">关于我们</p>
          <h2>{{ aboutUsDetail.title || '关于我们' }}</h2>
          <p class="story-sub" v-if="aboutUsDetail.subtitle">{{ aboutUsDetail.subtitle }}</p>
          <div class="story-body" v-html="aboutUsDetail.content"></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      baseUrl: '',
      carouselList: [],
      aboutUsDetail: {},
      newsList: [],
      wenjuxinxiRecommend: [],
      yuleshangpinRecommend: []
    }
  },
  computed: {
    aboutGallery() {
      return [
        this.aboutUsDetail.picture1,
        this.aboutUsDetail.picture2,
        this.aboutUsDetail.picture3
      ].filter(item => item)
    }
  },
  created() {
    this.baseUrl = this.$config.baseUrl
    this.getCarousel()
    this.getNewsList()
    this.getAboutUs()
    this.getList()
  },
  methods: {
    preHttp(str) {
      return str && str.substr(0, 4) === 'http'
    },
    frontImage(value) {
      if (!value) {
        return ''
      }
      const first = value.split(',')[0]
      return this.preHttp(first) ? first : this.baseUrl + first
    },
    getCarousel() {
      this.$http.get('config/list', { params: { page: 1, limit: 3 } }).then(res => {
        if (res.data.code === 0) {
          this.carouselList = res.data.data.list
        }
      })
    },
    getAboutUs() {
      this.$http.get('aboutus/detail/1', {}).then(res => {
        if (res.data.code === 0) {
          this.aboutUsDetail = res.data.data
        }
      })
    },
    getNewsList() {
      this.$http.get('news/list', {
        params: {
          page: 1,
          limit: 4,
          sort: 'addtime',
          order: 'desc'
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.newsList = res.data.data.list
        }
      })
    },
    getList() {
      this.$http.get('wenjuxinxi/autoSort', {
        params: {
          page: 1,
          limit: 8
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.wenjuxinxiRecommend = res.data.data.list
        }
      })

      this.$http.get('yuleshangpin/autoSort', {
        params: {
          page: 1,
          limit: 8
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.yuleshangpinRecommend = res.data.data.list
        }
      })
    },
    toDetail(path, item) {
      this.$router.push({ path: '/index/' + path, query: { detailObj: JSON.stringify(item) } })
    },
    moreBtn(path) {
      this.$router.push({ path: '/index/' + path })
    }
  }
}
</script>

<style lang="scss" scoped>
.home-showcase {
  display: flex;
  flex-direction: column;
  gap: 26px;
  padding-bottom: 18px;
}

.carousel-panel,
.product-panel,
.news-panel {
  border: 1px solid rgba(24, 37, 49, 0.08);
  border-radius: 34px;
  background: var(--front-paper);
  box-shadow: var(--front-shadow);
}

.carousel-panel,
.product-panel,
.news-panel {
  padding: 24px 26px 30px;
}

.home-carousel /deep/ .el-carousel__container {
  border-radius: 30px;
  overflow: hidden;
}

.home-image {
  width: 100%;
  height: 100%;
}

.story-panel {
  background: transparent;
  border: 0;
  box-shadow: none;
}

.story-card {
  border: 1px solid rgba(24, 37, 49, 0.08);
  border-radius: 28px;
  background: rgba(255, 255, 255, 0.78);
  box-shadow: 0 12px 30px rgba(24, 37, 49, 0.06);
}

.story-combined {
  overflow: hidden;
}

.story-visual {
  padding: 22px 22px 0;
}

.story-carousel /deep/ .el-carousel__container {
  border-radius: 24px;
  overflow: hidden;
}

.story-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.story-copy {
  padding: 28px 28px 26px;
}

.story-copy h2,
.panel-head h3,
.product-card h4,
.mini-card h4,
.news-card h4 {
  margin: 0;
  font-family: Georgia, "Times New Roman", serif;
}

.story-copy h2,
.panel-head h3 {
  font-size: clamp(28px, 4vw, 40px);
}

.panel-tag,
.product-type,
.news-date,
.story-sub {
  margin: 0;
  letter-spacing: 0.12em;
  text-transform: uppercase;
}

.panel-tag,
.product-type,
.news-date {
  color: var(--front-accent-deep);
  font-size: 11px;
}

.story-sub {
  margin-top: 10px;
  color: var(--front-teal);
  font-size: 12px;
}

.story-body {
  margin-top: 18px;
  color: var(--front-muted);
  line-height: 1.9;
  word-break: break-word;
  overflow-wrap: anywhere;
}

.story-body /deep/ p {
  margin: 0 0 12px;
  word-break: break-word;
  overflow-wrap: anywhere;
}

.story-body /deep/ * {
  max-width: 100%;
  word-break: break-word;
  overflow-wrap: anywhere;
}

.product-panel.alt {
  background: linear-gradient(180deg, rgba(255, 250, 241, 0.96), rgba(246, 237, 224, 0.92));
}

.panel-head {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  gap: 16px;
  margin-bottom: 18px;
}

.panel-link {
  border: 0;
  padding: 12px 18px;
  border-radius: 999px;
  background: rgba(24, 37, 49, 0.06);
  color: var(--front-ink);
  cursor: pointer;
  font: inherit;
  font-weight: 700;
  transition: all 0.2s ease;
}

.panel-link:hover {
  background: rgba(31, 116, 108, 0.14);
  color: var(--front-teal);
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 18px;
}

.product-card,
.mini-card,
.news-card {
  cursor: pointer;
  transition: transform 0.24s ease, box-shadow 0.24s ease;
}

.product-card:hover,
.mini-card:hover,
.news-card:hover,
.feature-lead:hover {
  transform: translateY(-4px);
}

.product-card {
  overflow: hidden;
  border-radius: 26px;
  border: 1px solid rgba(24, 37, 49, 0.08);
  background: rgba(255, 255, 255, 0.78);
}

.product-thumb {
  height: 250px;
  overflow: hidden;
  background: linear-gradient(135deg, #f3e8d6, #e4d5bd);
}

.product-thumb img {
  width: 100%;
  height: 100%;
  object-fit: contain;
  padding: 18px;
}

.product-meta {
  padding: 18px 18px 20px;
}

.product-meta h4 {
  margin-top: 10px;
  font-size: 24px;
}

.product-foot {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
  margin-top: 16px;
  color: var(--front-muted);
}

.product-foot strong,
.mini-card strong {
  color: var(--front-accent-deep);
  font-size: 18px;
}

.feature-split {
  display: grid;
  grid-template-columns: minmax(0, 1.08fr) 360px;
  gap: 18px;
}

.feature-lead {
  position: relative;
  overflow: hidden;
  min-height: 420px;
  border-radius: 30px;
}

.feature-lead img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.feature-overlay {
  position: absolute;
  right: 18px;
  bottom: 18px;
  left: 18px;
  padding: 18px 20px;
  border-radius: 20px;
  background: rgba(24, 37, 49, 0.58);
  color: #fff;
  backdrop-filter: blur(10px);
}

.feature-overlay p,
.mini-card p {
  margin: 0;
  font-size: 12px;
  letter-spacing: 0.12em;
  text-transform: uppercase;
}

.feature-overlay h4 {
  margin-top: 8px;
  font-size: 30px;
}

.feature-stack {
  display: grid;
  gap: 14px;
}

.mini-card {
  display: grid;
  grid-template-columns: 110px 1fr;
  gap: 14px;
  align-items: center;
  padding: 12px;
  border-radius: 24px;
  border: 1px solid rgba(24, 37, 49, 0.08);
  background: rgba(255, 255, 255, 0.78);
}

.mini-card img {
  width: 110px;
  height: 110px;
  object-fit: cover;
  border-radius: 18px;
}

.mini-card h4 {
  margin-top: 8px;
  font-size: 20px;
}

.mini-card strong {
  display: block;
  margin-top: 10px;
}

.news-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 18px;
}

.news-card {
  overflow: hidden;
  border-radius: 26px;
  border: 1px solid rgba(24, 37, 49, 0.08);
  background: rgba(255, 255, 255, 0.78);
}

.news-image {
  width: 100%;
  height: 220px;
  object-fit: cover;
  display: block;
}

.news-copy {
  padding: 18px;
}

.news-card h4 {
  margin-top: 10px;
  font-size: 22px;
  line-height: 1.28;
}

.news-intro {
  margin: 12px 0 0;
  color: var(--front-muted);
  line-height: 1.7;
}

@media (max-width: 1180px) {
  .feature-split,
  .product-grid,
  .news-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .carousel-panel,
  .product-panel,
  .news-panel {
    padding: 20px;
  }

  .story-visual {
    padding: 16px 16px 0;
  }

  .feature-stack {
    grid-template-columns: 1fr;
  }

  .mini-card {
    grid-template-columns: 1fr;
  }

  .mini-card img {
    width: 100%;
    height: 190px;
  }

  .home-carousel /deep/ .el-carousel__container {
    height: 280px !important;
  }

  .story-carousel /deep/ .el-carousel__container {
    height: 240px !important;
  }
}
</style>
