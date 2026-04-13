<template>
<div :style='{"width":"1200px","padding":"120px 7% 100px","margin":"0px auto","position":"relative","background":"url(http://codegen.caihongy.cn/20230109/138a4d606eae4a168810cef229969885.png) no-repeat center top,url(http://codegen.caihongy.cn/20230109/b95aeae1c1294f398aefb7c6cd5b16de.png) no-repeat center top,url(http://codegen.caihongy.cn/20230109/5ffc803e6682418eb7f0b09a98e35527.png) no-repeat center bottom,#faf0e6"}'>
    <el-form class="add-update-preview" ref="form" :model="form" :rules="rules" label-width="150px">
      <el-form-item :style='{"padding":"10px","boxShadow":"0 2px 0 #efe4d4","margin":"0 0 10px"}' label="联系人" prop="name">
        <el-input v-model="form.name" placeholder="联系人"></el-input>
      </el-form-item>
      <el-form-item :style='{"padding":"10px","boxShadow":"0 2px 0 #efe4d4","margin":"0 0 10px"}' label="手机号码" prop="phone">
        <el-input v-model="form.phone" placeholder="手机号码"></el-input>
      </el-form-item>
      <el-form-item :style='{"padding":"10px","boxShadow":"0 2px 0 #efe4d4","margin":"0 0 10px"}' label="默认地址">
        <el-radio-group v-model="form.isdefault">
          <el-radio label="是"></el-radio>
          <el-radio label="否"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item :style='{"padding":"10px","boxShadow":"0 2px 0 #efe4d4","margin":"0 0 10px"}' label="地址" prop="address">
        <el-input v-model="form.address" placeholder="请选择地址" readonly @click.native="popMap"></el-input>
      </el-form-item>
      <el-form-item :style='{"padding":"0","textAlign":"center","margin":"0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"4px","background":"#cca162","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' type="primary" @click="onSubmit('form')">添加</el-button>
        <el-button :style='{"border":"0px solid rgba(64, 158, 255, 1)","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#fff","borderRadius":"4px","background":"#009688","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="goBack">取消</el-button>
      </el-form-item>
    </el-form>

    <el-dialog id="myMapDialog" title="请选择收货地址" width="500px" :visible.sync="dialogVisible" top="5vh">
      <baidu-map class="map" :center="center" :zoom="zoom" :scroll-wheel-zoom="true" @ready="onBaiduMapReady" @click="getClickInfo">
        <bm-view style="width: 100%; height: 100%;" />
        <bm-marker :position="{lng: center.lng, lat: center.lat}" :dragging="true" animation="BMAP_ANIMATION_BOUNCE" />
        <bm-geolocation
          anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
          :showAddressBar="false"
          :autoLocation="false"
        ></bm-geolocation>
      </baidu-map>
      <div slot="footer">
        <el-input v-model="address" style="margin-bottom: 10px;" placeholder="请选择省市区、街道、小区"></el-input>
        <el-input v-model="detailAddress" style="margin-bottom: 15px;" placeholder="请填写门牌号（例如：8号楼2单元1201）"></el-input>
        <el-button class="locate-btn" :disabled="false" @click="locateUser">定位我</el-button>
        <el-button type="primary" @click="getAddr">确定</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </div>
    </el-dialog>
</div>
</template>

<script>
  import { Loading } from 'element-ui';
  export default {
    //数据集合
    data() {
      return {
        form: {
          userid: localStorage.getItem('userid'),
          name: localStorage.getItem('username'),
          phone: '',
          isdefault: '是',
          address: ''
        },
        rules: {
          name: [{ required: true, message: '请输入联系人', trigger: 'blur' }],
          phone: [
            { required: true, message: '请输入手机号码', trigger: 'blur' },
            { required: true, validator: this.$validate.isMobile, trigger: 'blur' }
          ],
          address: [{ required: true, message: '请选择收货地址', trigger: 'blur' }]
        },
        dialogVisible: false,
        BMap: null,
        map: null,
        mapReady: false,
        // center: { lat: 24.30457061, lng: 116.12640310 },
        center: { lng: 0, lat: 0 },
        address: '',
        detailAddress: '',
        zoom: 15,
        loadingInstance: null,
        isEdit: false
      }
    },
    created() {
      if (this.$route.query.id != undefined) {
        this.isEdit = true;
        this.form = Object.assign({}, JSON.parse(this.$route.query.editObj));
      }
      this.syncDialogAddressFromForm();
    },
    //方法集合
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$http.post(`address/${this.isEdit ? 'update' : 'add'}`, this.form).then(res => {
              if (res.data.code == 0) {
                this.$message({
                  message: `${this.isEdit ? '更新' : '添加'}成功`,
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.$router.go(-1);
                  }
                });
              }
            });
          } else {
            return false;
          }
        });
      },
      getAddr() {
        if (this.address == '') {
          this.$message({
            message: '地址不能为空',
            type: 'error',
            duration: 1500
          });
          return;
        }
        if (this.detailAddress && this.detailAddress.trim()) {
          this.form.address = `${this.address} ${this.detailAddress.trim()}`;
        } else {
          this.form.address = this.address;
        }
        this.dialogVisible = false;
      },
      goBack() {
        this.$router.go(-1);
      },
      syncDialogAddressFromForm() {
        const full = (this.form.address || '').trim();
        if (!full) {
          this.address = '';
          this.detailAddress = '';
          return;
        }
        const splitIdx = full.indexOf(' ');
        if (splitIdx > -1) {
          this.address = full.slice(0, splitIdx).trim();
          this.detailAddress = full.slice(splitIdx + 1).trim();
        } else {
          this.address = full;
          this.detailAddress = '';
        }
      },
      popMap() {
        this.syncDialogAddressFromForm();
        this.dialogVisible = true;
        if (this.loadingInstance) {
          this.loadingInstance.close();
          this.loadingInstance = null;
        }
      },
      onBaiduMapReady(e) {
        this.BMap = e.BMap;
        this.map = e.map;
        this.mapReady = true;
      },
      locateUser() {
        const that = this;
        if (this.loadingInstance) {
          this.loadingInstance.close();
        }
        this.loadingInstance = Loading.service({
          target: document.querySelector('#myMapDialog'),
          text: '定位中...',
          background: 'rgba(0, 0, 0, 0.3)',
          lock: true
        });

        const closeLoading = () => {
          if (that.loadingInstance) {
            that.loadingInstance.close();
            that.loadingInstance = null;
          }
        };

        const fallbackByBrowserGeo = () => {
          if (!navigator.geolocation) {
            closeLoading();
            that.$message.warning('当前浏览器不支持定位，请手动输入地址');
            return;
          }
          navigator.geolocation.getCurrentPosition(
            (pos) => {
              closeLoading();
              const lng = Number(pos.coords.longitude || 0);
              const lat = Number(pos.coords.latitude || 0);
              if (!lng || !lat) {
                that.$message.warning('定位失败，请手动输入地址');
                return;
              }
              that.center.lng = lng;
              that.center.lat = lat;
              that.reverseGeocodeByBaidu(lat, lng)
                .then((addrText) => {
                  if (addrText) {
                    that.address = addrText;
                    that.$message.success('已获取当前位置地址，可直接点击确定');
                    return;
                  }
                  that.address = `当前位置(经度:${lng.toFixed(6)}, 纬度:${lat.toFixed(6)})`;
                  that.$message.warning('当前只能定位到经纬度，建议点地图选点以补全到小区/门牌号');
                  that.openAddressPickGuide();
                })
                .catch(() => {
                  that.address = `当前位置(经度:${lng.toFixed(6)}, 纬度:${lat.toFixed(6)})`;
                  that.$message.warning('当前位置解析失败，建议点地图选点或手动填写详细地址');
                });
            },
            () => {
              closeLoading();
              that.$message.warning('定位失败，请手动输入地址');
            },
            { enableHighAccuracy: true, timeout: 8000 }
          );
        };

        if (!this.mapReady || !this.BMap || !this.map) {
          fallbackByBrowserGeo();
          return;
        }

        const geolocation = new this.BMap.Geolocation();
        geolocation.getCurrentPosition(function(res) {
          closeLoading();
          if (this.getStatus && this.getStatus() !== 0) {
            fallbackByBrowserGeo();
            return;
          }
          if (!res || !res.point) {
            fallbackByBrowserGeo();
            return;
          }
          that.center.lng = res.point.lng;
          that.center.lat = res.point.lat;
          that.map.setCenter(res.point);
          const addr = res.address || {};
          that.address = (addr.province || '') + (addr.city || '') + (addr.district || '') + (addr.street || '') + (addr.street_number || '');
        }, { enableHighAccuracy: true });

        setTimeout(() => {
          if (that.loadingInstance) {
            fallbackByBrowserGeo();
          }
        }, 8000);
      },
      reverseGeocodeByBaidu(lat, lng) {
        const url = `${this.$config.baseUrl}map/reverseGeocode?lat=${lat}&lng=${lng}`;
        return this.$http.get(url).then((res) => {
          const data = res && res.body ? res.body : {};
          if (data.code !== 0 || !data.data) {
            return '';
          }
          return data.data.address || '';
        }).catch(() => '');
      },
      openAddressPickGuide() {
        const url = `https://api.map.baidu.com/lbsapi/getpoint/index.html?ak=2mvCyHCf06q7q4ql9PkAgTTd6C2OdozW`;
        window.open(url, '_blank');
      },
      getClickInfo(e) {
        this.center.lng = e.point.lng
        this.center.lat = e.point.lat

        if (this.BMap) {
          let that = this
          const geoCoder = new this.BMap.Geocoder()
          geoCoder.getLocation(e.point, function(res) {
            const addrComponent = res.addressComponents
            const surroundingPois = res.surroundingPois
            const province = addrComponent.province
            const city = addrComponent.city
            const district = addrComponent.district
            let addr = addrComponent.street
            if (surroundingPois.length > 0 && surroundingPois[0].title) {
              if (addr) {
                addr += `-${surroundingPois[0].title}`
              } else {
                addr += `${surroundingPois[0].title}`
              }
            } else {
              addr += addrComponent.streetNumber
            }
            that.address = province + city + district + addr;
          })
        }
      },
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  	  padding: 0 10px 0 0;
	  	  color: #666;
	  	  font-weight: 500;
	  	  width: 150px;
	  	  font-size: 14px;
	  	  line-height: 40px;
	  	  text-align: right;
	  	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 150px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  	  border: 1px solid #e5d9c6;
	  	  border-radius: 4px;
	  	  padding: 0 12px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 400px;
	  	  font-size: 14px;
	  	  height: 40px;
	  	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  	  border: 1px solid #e5d9c6;
	  	  border-radius: 4px;
	  	  padding: 0 10px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 200px;
	  	  font-size: 14px;
	  	  height: 40px;
	  	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  	  border: 1px solid #e5d9c6;
	  	  border-radius: 4px;
	  	  padding: 0 10px 0 30px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 200px;
	  	  font-size: 14px;
	  	  height: 40px;
	  	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  	  border: 1px solid #e5d9c6;
	  	  cursor: pointer;
	  	  border-radius: 6px;
	  	  color: #333;
	  	  width: 100px;
	  	  font-size: 32px;
	  	  line-height: 100px;
	  	  text-align: center;
	  	  height: 100px;
	  	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  	  border: 1px solid #e5d9c6;
	  	  cursor: pointer;
	  	  border-radius: 6px;
	  	  color: #333;
	  	  width: 100px;
	  	  font-size: 32px;
	  	  line-height: 100px;
	  	  text-align: center;
	  	  height: 100px;
	  	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  	  border: 1px solid #e5d9c6;
	  	  cursor: pointer;
	  	  border-radius: 6px;
	  	  color: #333;
	  	  width: 100px;
	  	  font-size: 32px;
	  	  line-height: 100px;
	  	  text-align: center;
	  	  height: 100px;
	  	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  	  border: 1px solid #e5d9c6;
	  	  border-radius: 4px;
	  	  padding: 12px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 400px;
	  	  font-size: 14px;
	  	  height: 120px;
	  	}
    .map{
        height: 50vh;
    }
    .locate-btn{
        pointer-events: auto !important;
        opacity: 1 !important;
        cursor: pointer;
    }
    .locate-btn.is-disabled{
        pointer-events: auto !important;
        opacity: 1 !important;
    }
</style>
