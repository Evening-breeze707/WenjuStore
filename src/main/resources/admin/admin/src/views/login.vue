<template>
  <div class="admin-auth-shell">
    <div class="admin-auth-grid">
      <section class="admin-auth-promo">
        <p class="auth-tag">后台入口</p>
        <h1>文具商城后台</h1>
        <p class="auth-copy">
          用于商品、分类、订单、留言和用户信息的统一管理。
        </p>
      </section>

      <el-form class="admin-auth-form" ref="adminLoginForm" :model="rulesForm">
        <div class="admin-auth-head">
          <p class="auth-tag">登录</p>
          <h2>进入管理空间</h2>
          <p>请输入管理员账号和密码登录后台。</p>
        </div>

        <div v-if="loginType==1" class="field-block">
          <input class="auth-input" placeholder="用户名" name="username" type="text" v-model="rulesForm.username">
        </div>
        <div v-if="loginType==1" class="field-block">
          <input class="auth-input" placeholder="密码" name="password" type="password" v-model="rulesForm.password">
        </div>

        <div class="role-pills" v-if="roles.length>1">
          <el-radio v-for="item in roles" :key="item.roleName" v-model="rulesForm.role" :label="item.roleName">{{item.roleName}}</el-radio>
        </div>

        <div class="auth-actions">
          <el-button v-if="loginType==1" class="submit-button" type="primary" @click="login()">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      baseUrl: this.$base.url,
      loginType: 1,
      rulesForm: {
        username: "",
        password: "",
        role: "管理员",
        code: '',
      },
      roles: [{
        roleName: '管理员',
        tableName: 'users'
      }],
      tableName: "users",
      codes: [{
        num: 1,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      }, {
        num: 2,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      }, {
        num: 3,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      }, {
        num: 4,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      }],
    };
  },
  mounted() {
  },
  created() {
    this.getRandCode()
  },
  methods: {
    login() {
      if (!this.rulesForm.username) {
        this.$message.error("请输入用户名");
        return;
      }
      if (!this.rulesForm.password) {
        this.$message.error("请输入密码");
        return;
      }
      this.tableName = this.roles[0].tableName;
      this.rulesForm.role = this.roles[0].roleName;

      this.$http({
        url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
        method: "post"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$storage.set("Token", data.token);
          this.$storage.set("role", this.rulesForm.role);
          this.$storage.set("sessionTable", this.tableName);
          this.$storage.set("adminName", this.rulesForm.username);
          this.$router.replace({ path: "/index/" });
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    getRandCode(len = 4) {
      this.randomString(len)
    },
    randomString(len = 4) {
      let chars = [
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
        "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2",
        "3", "4", "5", "6", "7", "8", "9"
      ]
      let colors = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"]
      let sizes = ['14', '15', '16', '17', '18']

      for (let i = 0; i < len; i++) {
        let key = Math.floor(Math.random() * chars.length)
        this.codes[i].num = chars[key]
        let code = '#'
        for (let j = 0; j < 6; j++) {
          let colorKey = Math.floor(Math.random() * colors.length)
          code += colors[colorKey]
        }
        this.codes[i].color = code
        let rotate = Math.floor(Math.random() * 60)
        let plus = Math.floor(Math.random() * 2)
        if (plus == 1) rotate = '-' + rotate
        this.codes[i].rotate = 'rotate(' + rotate + 'deg)'
        let size = Math.floor(Math.random() * sizes.length)
        this.codes[i].size = sizes[size] + 'px'
      }
    },
  }
};
</script>

<style lang="scss" scoped>
.admin-auth-shell {
  min-height: 100vh;
  padding: 30px;
  display: grid;
  place-items: center;
}

.admin-auth-grid {
  display: grid;
  grid-template-columns: minmax(320px, 1fr) minmax(360px, 500px);
  width: 100%;
  max-width: 1120px;
  border: 1px solid var(--admin-line);
  border-radius: 36px;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.9);
  box-shadow: var(--admin-shadow);
}

.admin-auth-promo {
  padding: 52px 44px;
  background:
    radial-gradient(circle at top right, rgba(201, 106, 45, 0.18), transparent 24%),
    linear-gradient(160deg, #173043 0%, #204a62 100%);
  color: #fff;
}

.auth-tag {
  margin: 0;
  font-size: 11px;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  opacity: 0.72;
}

.admin-auth-promo h1,
.admin-auth-head h2 {
  margin: 16px 0 0;
  font-family: Georgia, "Times New Roman", serif;
}

.admin-auth-promo h1 {
  font-size: clamp(38px, 5vw, 58px);
  line-height: 0.98;
}

.auth-copy {
  max-width: 420px;
  margin: 20px 0 0;
  line-height: 1.9;
  color: rgba(255, 255, 255, 0.8);
}

.admin-auth-form {
  padding: 52px 42px;
}

.admin-auth-head p:last-child {
  margin: 12px 0 0;
  color: var(--admin-muted);
  line-height: 1.8;
}

.field-block {
  margin-top: 18px;
}

.auth-input {
  width: 100%;
  padding: 16px 18px;
  border: 1px solid var(--admin-line);
  border-radius: 18px;
  background: rgba(255, 255, 255, 0.92);
  color: var(--admin-ink);
  font: inherit;
  outline: none;
}

.auth-input:focus {
  border-color: rgba(43, 125, 120, 0.5);
  box-shadow: 0 0 0 4px rgba(43, 125, 120, 0.1);
}

.role-pills {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
}

.role-pills /deep/ .el-radio {
  margin: 0;
  padding: 10px 14px;
  border-radius: 999px;
  background: rgba(23, 48, 67, 0.05);
}

.auth-actions {
  margin-top: 24px;
}

.submit-button {
  width: 100%;
  height: 54px;
  border-radius: 999px !important;
  background: linear-gradient(135deg, var(--admin-accent), var(--admin-accent-deep)) !important;
  border-color: var(--admin-accent) !important;
  color: #fff !important;
  font-size: 16px;
}

@media (max-width: 900px) {
  .admin-auth-shell {
    padding: 16px;
  }

  .admin-auth-grid {
    grid-template-columns: 1fr;
  }
}
</style>
