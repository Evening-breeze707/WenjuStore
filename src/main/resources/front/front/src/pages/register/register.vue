<template>
  <div class="auth-shell register-shell">
    <div class="auth-grid register-grid">
      <section class="auth-promo">
        <p class="auth-tag">创建账号</p>
        <h1>加入文具商城</h1>
        <p class="auth-copy">
          注册后即可使用收藏、购物车、下单、地址管理和留言反馈等完整前台流程。
        </p>
        <div class="auth-tiles">
          <div class="auth-tile">
            <span>01</span>
            <strong>快速注册</strong>
            <p>在保留原注册逻辑的基础上，提供更统一的展示风格。</p>
          </div>
          <div class="auth-tile">
            <span>02</span>
            <strong>资料完善</strong>
            <p>头像、联系方式和个人中心等功能在注册后仍可正常使用。</p>
          </div>
        </div>
      </section>

      <el-form class="auth-form register-form" v-if="pageFlag==='register'" ref="registerForm" :model="registerForm" :rules="rules">
        <div class="auth-form-head">
          <p class="auth-tag">注册</p>
          <h2>创建新账号</h2>
          <p>填写下列信息后即可生成新的用户资料。</p>
        </div>

        <el-form-item class="register-item" prop="yonghuzhanghao">
          <el-input v-model="registerForm.yonghuzhanghao" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item class="register-item" prop="mima">
          <el-input v-model="registerForm.mima" type="password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item class="register-item" prop="mima">
          <el-input v-model="registerForm.mima2" type="password" placeholder="确认密码"></el-input>
        </el-form-item>
        <el-form-item class="register-item" prop="yonghuxingming">
          <el-input v-model="registerForm.yonghuxingming" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item class="register-item" prop="xingbie">
          <el-select v-model="registerForm.xingbie" placeholder="性别">
            <el-option
              v-for="(item,index) in yonghuxingbieOptions"
              :key="index"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="register-item" prop="nianling">
          <el-input v-model="registerForm.nianling" placeholder="年龄"></el-input>
        </el-form-item>
        <el-form-item class="register-item" prop="youxiang">
          <el-input v-model="registerForm.youxiang" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item class="register-item" prop="shoujihao">
          <el-input v-model="registerForm.shoujihao" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item class="register-item upload" prop="touxiang">
          <file-upload
            tip="上传头像"
            action="file/upload"
            :limit="1"
            :multiple="true"
            :fileUrls="registerForm.touxiang?registerForm.touxiang:''"
            @change="yonghutouxiangUploadChange"
          ></file-upload>
        </el-form-item>

        <div class="register-actions">
          <el-button class="submit-button" type="primary" @click="submitForm('registerForm')">注册</el-button>
          <el-button class="submit-button secondary" @click="resetForm('registerForm')">重置</el-button>
        </div>

        <router-link class="register-chip inline-link" to="/login">已有账号？去登录</router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageFlag: '',
      tableName: '',
      registerForm: {
        xingbie: ''
      },
      rules: {},
      yonghuxingbieOptions: []
    }
  },
  created() {
    this.pageFlag = this.$route.query.pageFlag
    this.tableName = this.$route.query.role || 'yonghu'
    this.rules.yonghuzhanghao = [{ required: true, message: '请输入账号', trigger: 'blur' }]
    this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
    this.rules.yonghuxingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
    this.yonghuxingbieOptions = '男,女'.split(',')
    this.rules.youxiang = [{ required: true, validator: this.$validate.isEmail, trigger: 'blur' }]
    this.rules.shoujihao = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }]
  },
  methods: {
    getUUID() {
      return new Date().getTime()
    },
    yonghutouxiangUploadChange(fileUrls) {
      this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl, 'g'), '')
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        if (this.registerForm.mima !== this.registerForm.mima2) {
          this.$message.error('两次密码输入不一致')
          return false
        }
        this.$http.post(this.tableName + '/register', this.registerForm).then(res => {
          if (res.data.code === 0) {
            this.$message({
              message: '注册成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.$router.push('/login')
              }
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })
        return true
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.auth-shell {
  min-height: 100vh;
  padding: 30px;
  display: grid;
  place-items: center;
}

.register-grid {
  display: grid;
  grid-template-columns: minmax(320px, 0.92fr) minmax(420px, 0.98fr);
  width: 100%;
  max-width: 1240px;
  border: 1px solid rgba(24, 37, 49, 0.08);
  border-radius: 36px;
  overflow: hidden;
  background: rgba(255, 250, 241, 0.88);
  backdrop-filter: blur(16px);
  box-shadow: var(--front-shadow);
}

.auth-promo {
  padding: 54px 46px;
  background:
    radial-gradient(circle at top right, rgba(217, 111, 58, 0.18), transparent 28%),
    linear-gradient(160deg, #173043 0%, #1f4f64 100%);
  color: #fff;
}

.auth-tag {
  margin: 0;
  font-size: 11px;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  opacity: 0.72;
}

.auth-promo h1,
.auth-form-head h2 {
  margin: 16px 0 0;
  font-family: Georgia, "Times New Roman", serif;
}

.auth-promo h1 {
  font-size: clamp(36px, 5vw, 62px);
  line-height: 0.98;
}

.auth-copy {
  max-width: 420px;
  margin: 20px 0 0;
  line-height: 1.9;
  color: rgba(255, 255, 255, 0.8);
}

.auth-tiles {
  display: grid;
  gap: 14px;
  margin-top: 30px;
}

.auth-tile {
  padding: 20px 22px;
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.08);
}

.auth-tile span {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 34px;
  height: 34px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.14);
  font-size: 12px;
  font-weight: 700;
}

.auth-tile strong {
  display: block;
  margin-top: 14px;
  font-size: 18px;
}

.auth-tile p {
  margin: 8px 0 0;
  color: rgba(255, 255, 255, 0.72);
  line-height: 1.7;
}

.register-form {
  padding: 48px 40px 36px;
}

.auth-form-head p:last-child {
  margin: 12px 0 0;
  color: var(--front-muted);
  line-height: 1.8;
}

.register-item {
  margin-bottom: 16px;
}

.register-actions {
  display: flex;
  gap: 12px;
  margin-top: 6px;
}

.submit-button {
  flex: 1;
  height: 52px;
  border-radius: 999px !important;
  background: linear-gradient(135deg, var(--front-accent), var(--front-accent-deep)) !important;
  border-color: var(--front-accent) !important;
  color: #fff !important;
  font-size: 16px;
}

.submit-button.secondary {
  background: rgba(24, 37, 49, 0.08) !important;
  border-color: rgba(24, 37, 49, 0.08) !important;
  color: var(--front-ink) !important;
}

.inline-link {
  display: inline-flex;
  margin-top: 16px;
}

.register-chip {
  padding: 11px 14px;
  border-radius: 999px;
  background: rgba(31, 116, 108, 0.08);
  color: var(--front-teal);
  text-decoration: none;
  font-weight: 700;
}

@media (max-width: 960px) {
  .auth-shell {
    padding: 16px;
  }

  .register-grid {
    grid-template-columns: 1fr;
  }

  .register-actions {
    flex-direction: column;
  }
}
</style>
