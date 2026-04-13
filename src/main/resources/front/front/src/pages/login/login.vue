<template>
  <div class="auth-shell">
    <div class="auth-grid">
      <section class="auth-promo">
        <p class="auth-tag">会员入口</p>
        <h1>文具商城</h1>
        <p class="auth-copy">
          精选文具与创意周边，带来更轻松、更高效的书写与学习体验。
        </p>
        <div class="auth-tiles">
          <div class="auth-tile">
            <strong>发现你喜欢的文具</strong>
            <p>从日常书写到学习收纳，快速找到适合自己的商品。</p>
          </div>
          <div class="auth-tile">
            <strong>轻松完成下单流程</strong>
            <p>登录后即可使用收藏、购物车、地址管理和订单服务。</p>
          </div>
        </div>
      </section>

      <el-form ref="loginForm" :model="loginForm" class="auth-form" :rules="rules">
        <div class="auth-form-head">
          <p class="auth-tag">登录</p>
          <h2>欢迎回来</h2>
          <p>请输入账号和密码，继续浏览与购买商品。</p>
        </div>

        <el-form-item v-if="loginType===1" class="list-item" prop="username">
          <input class="auth-input" v-model="loginForm.username" placeholder="请输入账号">
        </el-form-item>

        <el-form-item v-if="loginType===1" class="list-item" prop="password">
          <input class="auth-input" v-model="loginForm.password" placeholder="请输入密码" type="password">
        </el-form-item>

        <el-form-item v-if="roles.length>1" class="list-type" prop="role">
          <div class="role-pills">
            <el-radio
              v-model="loginForm.tableName"
              :label="item.tableName"
              v-for="(item, index) in roles"
              :key="index"
              @change.native="getCurrentRow(item)"
            >{{item.roleName}}</el-radio>
          </div>
        </el-form-item>

        <el-form-item class="auth-submit">
          <el-button v-if="loginType===1" class="submit-button" @click="submitForm('loginForm')">登录</el-button>
        </el-form-item>

        <div class="register-links">
          <router-link
            class="register-chip"
            v-for="(item, index) in roles"
            :key="index"
            v-if="item.canRegister"
            :to="{path: '/register', query: {role: item.tableName,pageFlag:'register'}}"
          >
            注册{{item.roleName}}
          </router-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      baseUrl: this.$config.baseUrl,
      loginType: 1,
      roles: [
        {
          roleName: '用户',
          tableName: 'yonghu',
          canRegister: true
        }
      ],
      loginForm: {
        username: '',
        password: '',
        tableName: 'yonghu',
        code: ''
      },
      role: '用户',
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getCurrentRow(row) {
      this.role = row.roleName
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        this.$http.get(`${this.loginForm.tableName}/login`, { params: this.loginForm }).then(res => {
          if (res.data.code === 0) {
            localStorage.setItem('Token', res.data.token)
            localStorage.setItem('UserTableName', this.loginForm.tableName)
            localStorage.setItem('username', this.loginForm.username)
            localStorage.setItem('adminName', this.loginForm.username)
            localStorage.setItem('sessionTable', this.loginForm.tableName)
            localStorage.setItem('role', this.role)
            localStorage.setItem('keyPath', this.$config.indexNav.length + 2)
            this.$router.push('/index/center')
            this.$message({
              message: '登录成功',
              type: 'success',
              duration: 1500
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })
        return true
      })
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

.auth-grid {
  display: grid;
  grid-template-columns: minmax(320px, 1fr) minmax(360px, 520px);
  width: 100%;
  max-width: 1180px;
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
  font-size: clamp(38px, 5vw, 64px);
  line-height: 0.96;
}

.auth-copy {
  max-width: 460px;
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
  backdrop-filter: blur(8px);
}

.auth-tile strong {
  display: block;
  font-size: 18px;
  font-size: 18px;
}

.auth-tile p {
  margin: 10px 0 0;
  color: rgba(255, 255, 255, 0.72);
  line-height: 1.7;
}

.auth-form {
  padding: 54px 44px 40px;
}

.auth-form-head p:last-child {
  margin: 12px 0 0;
  color: var(--front-muted);
  line-height: 1.8;
}

.list-item,
.auth-submit,
.list-type {
  margin-bottom: 18px;
}

.auth-input {
  width: 100%;
  padding: 16px 18px;
  border: 1px solid rgba(24, 37, 49, 0.12);
  border-radius: 18px;
  background: rgba(255, 255, 255, 0.82);
  color: var(--front-ink);
  font: inherit;
  outline: none;
}

.auth-input:focus {
  border-color: rgba(31, 116, 108, 0.5);
  box-shadow: 0 0 0 4px rgba(31, 116, 108, 0.1);
}

.role-pills /deep/ .el-radio {
  display: inline-flex;
  align-items: center;
  margin: 0 10px 10px 0;
  padding: 10px 14px;
  border-radius: 999px;
  background: rgba(24, 37, 49, 0.05);
}

.role-pills /deep/ .el-radio__input.is-checked + .el-radio__label {
  color: var(--front-accent-deep);
  font-weight: 700;
}

.submit-button {
  width: 100%;
  height: 54px;
  border-radius: 999px !important;
  background: linear-gradient(135deg, var(--front-accent), var(--front-accent-deep)) !important;
  border-color: var(--front-accent) !important;
  color: #fff !important;
  font-size: 16px;
}

.register-links {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 10px;
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

  .auth-grid {
    grid-template-columns: 1fr;
  }
}
</style>
