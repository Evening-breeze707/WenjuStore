<template>
<div class="order-page" :style='{"width":"100%","maxWidth":"1320px","padding":"40px 24px 70px","margin":"0px auto","position":"relative","boxSizing":"border-box","background":"linear-gradient(145deg, rgba(250, 253, 255, 0.98), rgba(238, 246, 255, 0.94) 54%, rgba(247, 251, 255, 0.94))"}'>
    <el-button :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"0 5px 0 0","outline":"none","color":"#fff","borderRadius":"4px","background":"#4d8fd8","width":"auto","lineHeight":"40px","fontSize":"14px","height":"40px"}' type="warning" size="mini" @click="backClick" class="el-icon-back">返回</el-button>
    <div class="section-title" :style='{"margin":"10px 0","color":"#333","textAlign":"center","background":"none","width":"100%","fontSize":"22px","lineHeight":"54px","fontWeight":"600"}'>我的订单</div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="未支付" name="未支付"></el-tab-pane>
      <el-tab-pane label="已支付" name="已支付"></el-tab-pane>
      <el-tab-pane label="已发货" name="已发货"></el-tab-pane>
      <el-tab-pane label="已完成" name="已完成"></el-tab-pane>
      <el-tab-pane label="已退款" name="已退款"></el-tab-pane>
      <el-tab-pane label="已取消" name="已取消"></el-tab-pane>
    </el-tabs>
    <el-table class="order-table" :data="tableData" style="width: 100%">
      <el-table-column label="订单编号" prop="orderid"></el-table-column>
      <el-table-column label="商品" align="center" width="200px">
        <template slot-scope="scope">
          <div class="shangpin">
            <el-image style="width: 100px; height: 100px" :src="baseUrl + scope.row.picture" fit="fill"></el-image>
            <span style="margin-left: 10px" >{{ scope.row.goodname }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="价格">
        <template slot-scope="scope">
          <span v-if="scope.row.type == 1 || scope.row.type == 3"><span :style='{"fontSize":"12px"}'>￥</span>{{ scope.row.price }}</span>
          <span v-if="scope.row.type == 2">{{ scope.row.price }}积分</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" prop="buynumber"></el-table-column>
      <el-table-column label="总价">
        <template slot-scope="scope">
          <span v-if="scope.row.type == 1 || scope.row.type == 3"><span :style='{"fontSize":"12px"}'>￥</span>{{ scope.row.total }}</span>
          <span v-if="scope.row.type == 2">{{ scope.row.total }}积分</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" prop="address"></el-table-column>
      <el-table-column label="电话" prop="tel"></el-table-column>
      <el-table-column label="收货人" prop="consignee"></el-table-column>
      <el-table-column label="下单时间" prop="addtime"></el-table-column>
      <el-table-column label="备注" prop="remark"></el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template slot-scope="scope">
          <div class="order-actions">
            <el-button v-show="activeName == '未支付'" type="success" size="mini" @click="pay(scope.row)">支付</el-button>
            <el-button v-show="activeName == '未支付'" type="danger" size="mini" @click="cancel(scope.row)">取消</el-button>
            <el-button v-show="activeName == '已支付'" type="danger" size="mini" @click="refund(scope.row)">取消并退款</el-button>
            <el-button v-if="scope.row.logistics" type="warning" size="mini" @click="logistics(scope.row)">物流</el-button>
            <el-button v-show="activeName == '已完成'" type="danger" size="mini" @click="returnGood(scope.row)">退货</el-button>
            <el-button v-show="activeName == '已完成'" type="primary" size="mini" @click="toDetail(scope.row)">评价</el-button>
            <el-button v-show="activeName == '已发货'" type="success" size="mini" @click="confirm(scope.row)">确认收货</el-button>
            <el-button v-show="activeName == '已完成' || activeName == '已取消' || activeName == '已退款'" type="info" size="mini" @click="deleteOrder(scope.row)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    
	<el-pagination
	  background
	  class="pagination"
	  :pager-count="7"
	  :page-size="pageSize"
	  :page-sizes="pageSizes"
	  prev-text="<"
	  next-text=">"
	  :hide-on-single-page="true"
	  :layout='["total","prev","pager","next","sizes","jumper"].join()'
	  :total="total"
	  :style='{"padding":"0","margin":"10px auto","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"100%","fontWeight":"500"}'
	  @current-change="curChange"
	  @prev-click="prevClick"
	  @next-click="nextClick"
	></el-pagination>

    <el-dialog title="物流信息" :visible.sync="logisticsVisible" width="30%">
        <div v-html="logisticsText"></div>
    </el-dialog>
	
</div>
</template>

<script>
  import config from '@/config/config'
  export default {
    data() {
      return {
		layouts: '',
        baseUrl: config.baseUrl,
        activeName: '未支付',
        tableData: [],
        total: 1,
        pageSize: 10,pageSizes: [10,20,30,50],
        totalPage: 1,
        session: {},
        userTableName: localStorage.getItem('UserTableName'),
        logisticsVisible: false,
        logisticsText: ''
      }
    },
    created() {
      this.getSession();
      this.getMyOrderList(1);
    },
    methods: {
      backClick() {
          this.$router.push('/index/center')
      },
      getSession() {
        this.$http.get(this.userTableName+'/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.session = res.data.data;
          }
        });
      },
      handleClick(tab, event) {
        tab.activeName = event.target.outerText;
        this.getMyOrderList(1);
      },
      toDetail(row) {
          this.$router.push({path: '/index/'+row.tablename+'Detail', query: {discussObj: JSON.stringify(row)}});
      },
      handleAction(index, row) {
        if (index == '已支付' && this.session.money < row.total) {
          this.$message.error('余额不足，请先充值');
          return;
        }

        row.status = index;
        this.$http.post('orders/update', row).then(res => {
          if (res.data.code == 0) {
            if (index == '已支付') this.session.money -= row.total;
            if (index == '已退款' && row.type == 1) this.session.money += row.total;
            if (index == '已退款' && row.type == 2) this.session.jifen += row.total;
            if (index == '已支付' || index == '已退款') {
              this.session.money = this.session.money.toFixed(2);
              this.$http.post(this.userTableName+'/update', this.session).then(res => {});
            }
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getMyOrderList(1);
              }
            });
          }
        });
      },
        // 退款
        refund(item) {
            this.$confirm(`确定退款吗？退款金额将返回账户中。`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                // 获取商品详情信息
                this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                    // 商品信息
                    let good = res.data.data;
                    // 用户当前用户信息
                    let table = localStorage.getItem("UserTableName");
                    this.$http.get(table+'/session', {}).then(res => {
                        this.user = res.data.data;
                        if(item.type==1||item.type==3) {
                            // 如果该商品存在积分
                            if (good.jf) {
                                this.user.jf = Number(this.user.jf) - Number(item.price * item.buynumber);
                            }
                            this.user.money = Number(this.user.money) + Number(item.total);
                            // 更新用户余额
                            this.$http.post(table+'/update', this.user).then(res => {
                                item.status = '已退款'
                                this.$http.post(`orders/update`, item).then(res => {
                                    if(item.tablename == `wenjuxinxi`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`wenjuxinxi/update`, good).then(res => {
                                        });
                                    }
                                    if(item.tablename == `yuleshangpin`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`yuleshangpin/update`, good).then(res => {
                                        });
                                    }
                                    this.$message({
                                      message: '退款成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                        this.getMyOrderList(1);
                                      }
                                    });
                                });
                            });
                        } else if(item.type==2) {
                            this.user.jf = Number(this.user.jf) + Number(item.total);
                            // 更新用户余额
                            this.$http.post(table+'/update', this.user).then(res => {
                                item.status = '已退款'
                                this.$http.post(`orders/update`, item).then(res => {
                                    if(item.tablename == `wenjuxinxi`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`wenjuxinxi/update`, good).then(res => {
                                        });
                                    }
                                    if(item.tablename == `yuleshangpin`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`yuleshangpin/update`, good).then(res => {
                                        });
                                    }
                                    this.$message({
                                      message: '退款成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                        this.getMyOrderList(1);
                                      }
                                    });
                                });
                            });

                        }
                    });
                });
            });
        },
        //物流
        logistics(row) {
            this.logisticsText = row.logistics;
            this.logisticsVisible = true;
        },
        //退货
        returnGood(item) {
            this.$confirm(`确定退货吗？退款金额将返回账户中。`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                // 获取商品详情信息
                this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                    // 商品信息
                    let good = res.data.data;
                    // 用户当前用户信息
                    let table = localStorage.getItem("UserTableName");
                    this.$http.get(table+'/session', {}).then(res => {
                        this.user = res.data.data;
                        if(item.type==1||item.type==3) {
                            // 如果该商品存在积分
                            if (good.jf) {
                                this.user.jf = Number(this.user.jf) - Number(item.price * item.buynumber);
                            }
                            this.user.money = Number(this.user.money) + Number(item.total);
                            // 更新用户余额
                            this.$http.post(table+'/update', this.user).then(res => {
                                item.status = '已退款'
                                this.$http.post(`orders/update`, item).then(res => {
                                    if(item.tablename == `wenjuxinxi`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`wenjuxinxi/update`, good).then(res => {
                                        });
                                    }
                                    if(item.tablename == `yuleshangpin`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`yuleshangpin/update`, good).then(res => {
                                        });
                                    }
                                    this.$message({
                                      message: '退货成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                        this.getMyOrderList(1);
                                      }
                                    });
                                });
                            });
                        } else if(item.type==2) {
                            this.user.jf = Number(this.user.jf) + Number(item.total);
                            // 更新用户余额
                            this.$http.post(table+'/update', this.user).then(res => {
                                item.status = '已退款'
                                this.$http.post(`orders/update`, item).then(res => {
                                    if(item.tablename == `wenjuxinxi`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`wenjuxinxi/update`, good).then(res => {
                                        });
                                    }
                                    if(item.tablename == `yuleshangpin`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`yuleshangpin/update`, good).then(res => {
                                        });
                                    }
                                    this.$message({
                                      message: '退货成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                        this.getMyOrderList(1);
                                      }
                                    });
                                });
                            });
                        }
                    });
                });
            });
        },

        // 支付
        pay(item) {
            // 获取商品详情信息
            this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                // 商品信息
                let data = res.data.data;
                // 用户当前用户信息
                let table = localStorage.getItem("UserTableName");
                this.$http.get(table+'/session', {}).then(res => {
                    this.user = res.data.data;
                    // 判断余额是否充足
                    if (Number(this.user.money) < Number(item.total)) {
                        this.$message({
                            message: '余额不足，请先充值',
                            type: 'error',
                            duration: 1500
                        });
                        return
                    }
                    // 如果该商品存在积分
                    if (data.jf) {
                        this.user.jf = Number(this.user.jf) + Number(data.jf * item.buynumber);
                    }
                    this.user.money = this.user.money - item.total;
                    // 更新用户余额
                    this.$http.post(table+'/update', this.user).then(res => {
                        item.status = '已支付'
                        this.$http.post(`orders/update`, item).then(res => {
                            this.$message({
                              message: '支付成功',
                              type: 'success',
                              duration: 1500,
                              onClose: () => {
                                this.getMyOrderList(1);
                              }
                            });
                        });
                    });
                });
            });
        },
      confirm(item){
          this.$confirm(`是否确定收货？`, "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              item.status = '已完成'
              this.$http.post(`orders/update`, item).then(res => {
                  this.$message({
                    message: '确认收货成功',
                    type: 'success',
                    duration: 1500,
                    onClose: () => {
                      this.getMyOrderList(1);
                    }
                  });
              });
          });
      },
      cancel(item){
          this.$confirm(`是否取消订单？`, "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              item.status = '已取消'
              this.$http.post(`orders/update`, item).then(res => {
                    // 获取商品详情信息
                    this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                        // 商品信息
                        let good = res.data.data;
                        if(item.tablename == `wenjuxinxi`){
                            // 加回库存数量
                            good.alllimittimes = good.alllimittimes + item.buynumber;
                            this.$http.post(`wenjuxinxi/update`, good).then(res => {
                            });
                        }
                  });
                    // 获取商品详情信息
                    this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                        // 商品信息
                        let good = res.data.data;
                        if(item.tablename == `yuleshangpin`){
                            // 加回库存数量
                            good.alllimittimes = good.alllimittimes + item.buynumber;
                            this.$http.post(`yuleshangpin/update`, good).then(res => {
                            });
                        }
                  });
                  this.$message({
                    message: '订单取消成功',
                    type: 'success',
                    duration: 1500,
                    onClose: () => {
                      this.getMyOrderList(1);
                    }
                  });
              });
          });
      },
      deleteOrder(item) {
          this.$confirm(`确定删除这条订单记录吗？删除后无法恢复。`, "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              this.$http.post(`orders/delete`, [item.id]).then(res => {
                  if (res.data.code === 0) {
                      this.$message({
                        message: '订单删除成功',
                        type: 'success',
                        duration: 1500,
                        onClose: () => {
                          this.getMyOrderList(1);
                        }
                      });
                  } else {
                      this.$message.error(res.data.msg || '订单删除失败');
                  }
              });
          });
      },
      getMyOrderList(page) {
        this.$http.get('orders/list', {params: {page, limit: this.pageSize,sort:'addtime',order:'desc', userid: localStorage.getItem('userid'), status: this.activeName}}).then(res => {
          if (res.data.code == 0) {
            this.tableData = res.data.data.list;
            this.total = res.data.data.total;
            this.pageSize = res.data.data.pageSize;this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
            this.totalPage = res.data.data.totalPage;
          }
        });
      },
      curChange(page) {
        this.getMyOrderList(page);
      },
      prevClick(page) {
        this.getMyOrderList(page);
      },
      nextClick(page) {
        this.getMyOrderList(page);
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.order-page {
	  border: 1px solid rgba(36, 50, 66, 0.08);
	  border-radius: 28px;
	  overflow: hidden;
	  box-shadow: var(--front-shadow);
	}

	.section {
	  width: 1000px;
	  margin: 0 auto;
	}

	.shangpin {
	  display: flex;
	  align-items: center;
	  min-width: 0;
	}

	.order-table {
	  margin-top: 12px;
	  border-radius: 18px !important;
	}

	.order-table /deep/ .el-table__body-wrapper,
	.order-table /deep/ .el-table__fixed-right {
	  z-index: 2;
	}

	.order-table /deep/ th,
	.order-table /deep/ td {
	  padding: 12px 10px;
	}

	.order-table /deep/ .cell {
	  word-break: break-word;
	  line-height: 1.55;
	}

	.order-actions {
	  display: flex;
	  flex-wrap: wrap;
	  justify-content: center;
	  gap: 6px;
	  min-width: 132px;
	}

	.order-actions /deep/ .el-button {
	  margin: 0 !important;
	  min-width: 54px;
	  padding: 8px 10px !important;
	}

	@media (max-width: 960px) {
	  .order-page {
	    padding-left: 12px !important;
	    padding-right: 12px !important;
	  }
	}
	
	.el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	.el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	.el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	.el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	.el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: inline-block;
		vertical-align: top;
	}
	
	.el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #666;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #f4f4f5;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #2367ad;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #f4f4f5;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pager .number.active {
		cursor: default;
		padding: 0 4px;
		margin: 0 5px;
		color: #FFF;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #4d8fd8;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 13px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 28px;
		text-align: center;
	}
	
	.el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 14px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
</style>
