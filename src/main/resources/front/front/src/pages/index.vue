<template>
  <div class="site-shell">
    <div class="site-glow site-glow-a"></div>
    <div class="site-glow site-glow-b"></div>
    <div class="site-pencil"></div>

    <header class="site-header">
      <div class="header-top">
        <div class="brand-block" @click="$router.push('/index/home')">
          <div class="brand-mark">WS</div>
          <div class="brand-copy">
            <p class="brand-kicker">Stationery Store</p>
            <h1 class="brand-title">文具商城</h1>
            <p class="brand-desc">为学习、办公和日常创作挑选好用文具</p>
          </div>
        </div>

        <div class="account-block">
          <div class="account-chip">
            <span class="account-label">当前账号</span>
            <strong>{{ Token ? username : '游客' }}</strong>
          </div>
          <el-button v-if="!Token" class="hero-action secondary" @click="toLogin()">登录</el-button>
          <el-button v-if="Token" class="hero-action ghost" @click="logout">退出</el-button>
        </div>
      </div>

      <nav class="site-nav">
        <button
          v-for="(menu, index) in menuList"
          :key="menu.url"
          class="nav-pill"
          :class="{ active: activeIndex === String(index) }"
          @click="handleNav(menu.url, index)"
        >
          {{ menu.name }}
        </button>
        <button
          class="nav-pill utility"
          :class="{ active: activeIndex === String(menuList.length) }"
          @click="handleUtility('/index/cart', menuList.length)"
        >
          购物车
        </button>
        <button
          v-if="Token && notAdmin"
          class="nav-pill utility"
          :class="{ active: activeIndex === String(menuList.length + 1) }"
          @click="handleUtility('/index/center', menuList.length + 1)"
        >
          个人中心
        </button>
      </nav>
    </header>

    <main class="site-main">
      <router-view></router-view>
    </main>

    <footer class="site-footer">
      <div>
        <p class="footer-title">文具商城</p>
        <p class="footer-text">精选文具与周边商品展示平台。</p>
      </div>
      <div class="footer-links">
        <button class="footer-link" @click="handleNav('/index/home', 0)">首页</button>
        <button class="footer-link" @click="handleNav('/index/messages', 4)">留言</button>
      </div>
    </footer>
  </div>
</template>

<script>
import Vue from 'vue'

export default {
  data() {
    return {
      activeIndex: '0',
      menuList: [],
      Token: localStorage.getItem('Token'),
      username: localStorage.getItem('username'),
      notAdmin: localStorage.getItem('sessionTable') !== 'users'
    }
  },
  created() {
    this.menuList = this.$config.indexNav
    this.syncNavState()
  },
  watch: {
    $route() {
      this.Token = localStorage.getItem('Token')
      this.username = localStorage.getItem('username')
      this.notAdmin = localStorage.getItem('sessionTable') !== 'users'
      this.syncNavState()
      window.scrollTo(0, 0)
    }
  },
  methods: {
    syncNavState() {
      const path = this.$route.path
      let matched = false
      this.menuList.forEach((menu, index) => {
        if (path === menu.url) {
          this.activeIndex = String(index)
          localStorage.setItem('keyPath', this.activeIndex)
          matched = true
        }
      })
      if (!matched) {
        this.activeIndex = localStorage.getItem('keyPath') || '0'
      }
    },
    handleNav(path, index) {
      this.activeIndex = String(index)
      localStorage.setItem('keyPath', this.activeIndex)
      this.$router.push(path)
    },
    handleUtility(path, index) {
      if (!localStorage.getItem('Token')) {
        this.toLogin()
        return
      }
      this.activeIndex = String(index)
      localStorage.setItem('keyPath', this.activeIndex)
      this.$router.push(path)
    },
    toLogin() {
      this.$router.push('/login')
    },
    logout() {
      localStorage.clear()
      Vue.http.headers.common.Token = ''
      this.activeIndex = '0'
      localStorage.setItem('keyPath', this.activeIndex)
      this.Token = ''
      this.$router.push('/index/home')
      this.$message({
        message: '退出成功',
        type: 'success',
        duration: 1000
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.site-shell {
  position: relative;
  min-height: 100vh;
  padding: 24px clamp(14px, 3vw, 34px);
  color: var(--front-ink);
}

.site-glow {
  position: fixed;
  border-radius: 999px;
  filter: blur(12px);
  opacity: 0.65;
  pointer-events: none;
}

.site-glow-a {
  top: 12%;
  right: -80px;
  width: 260px;
  height: 260px;
  background: rgba(77, 143, 216, 0.18);
}

.site-glow-b {
  bottom: 18%;
  left: -90px;
  width: 320px;
  height: 320px;
  background: rgba(63, 158, 170, 0.14);
}

.site-pencil {
  position: fixed;
  right: -90px;
  bottom: 72px;
  width: 420px;
  height: 32px;
  border-radius: 999px;
  background:
    linear-gradient(90deg, #b8d8ff 0 12%, #3f9eaa 12% 80%, #eef6ff 80% 88%, #2367ad 88% 100%);
  box-shadow: 0 18px 36px rgba(36, 50, 66, 0.14);
  opacity: 0.18;
  transform: rotate(-18deg);
  pointer-events: none;
}

.site-header {
  position: sticky;
  top: 14px;
  z-index: 20;
  display: flex;
  flex-direction: column;
  gap: 14px;
  max-width: 1320px;
  margin: 0 auto;
  padding: 16px 18px 14px;
  border: 1px solid rgba(24, 37, 49, 0.08);
  border-radius: 30px;
  background: rgba(250, 253, 255, 0.88);
  backdrop-filter: blur(16px);
  box-shadow: var(--front-shadow);
}

.header-top {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 24px;
  width: 100%;
}

.brand-block {
  display: flex;
  align-items: center;
  gap: 14px;
  cursor: pointer;
}

.brand-mark {
  display: grid;
  place-items: center;
  width: 60px;
  height: 60px;
  border-radius: 20px;
  background:
    linear-gradient(135deg, var(--front-green), var(--front-teal));
  color: #fff;
  font-family: Georgia, "Times New Roman", serif;
  font-size: 28px;
  font-weight: 700;
}

.brand-copy {
  min-width: 0;
}

.brand-kicker,
.footer-title {
  margin: 0;
  letter-spacing: 0.16em;
  text-transform: uppercase;
}

.brand-kicker {
  color: var(--front-muted);
  font-size: 10px;
}

.brand-title {
  margin: 4px 0 0;
  font-family: Georgia, "Times New Roman", serif;
  font-size: 30px;
}

.brand-desc {
  margin: 5px 0 0;
  color: var(--front-muted);
  font-size: 13px;
}

.site-nav {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 10px;
  width: 100%;
  padding: 10px;
  border-radius: 22px;
  background: rgba(36, 50, 66, 0.045);
}

.nav-pill,
.footer-link {
  border: 0;
  background: transparent;
  color: var(--front-ink);
  cursor: pointer;
  font: inherit;
}

.nav-pill {
  padding: 11px 18px;
  border-radius: 999px;
  transition: all 0.25s ease;
  font-weight: 700;
}

.nav-pill:hover,
.nav-pill.active {
  background: #fff;
  color: var(--front-green);
  box-shadow: 0 8px 18px rgba(36, 50, 66, 0.08);
}

.nav-pill.utility {
  margin-left: auto;
  background: rgba(47, 143, 103, 0.1);
  color: var(--front-green);
}

.nav-pill.utility + .nav-pill.utility {
  margin-left: 0;
}

.account-block {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 10px;
}

.account-chip {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding: 10px 14px;
  border-radius: 20px;
  background: #fff;
  box-shadow: inset 0 0 0 1px rgba(36, 50, 66, 0.08);
}

.account-label {
  color: var(--front-muted);
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 0.14em;
}

.site-main {
  position: relative;
  z-index: 1;
  max-width: 1320px;
  margin: 0 auto;
  padding-top: 24px;
}

.hero-action {
  min-width: 110px;
}

.hero-action.secondary {
  background: linear-gradient(135deg, var(--front-accent), var(--front-accent-deep)) !important;
  border-color: var(--front-accent) !important;
  color: #fff !important;
}

.hero-action.ghost {
  min-width: 110px;
}

.site-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 16px;
  max-width: 1320px;
  margin-top: 24px;
  margin-left: auto;
  margin-right: auto;
  padding: 20px 26px;
  border: 1px solid rgba(24, 37, 49, 0.08);
  border-radius: 32px;
  background: var(--front-paper);
  box-shadow: var(--front-shadow);
}

.footer-title {
  color: var(--front-accent-deep);
  font-size: 11px;
}

.footer-text {
  margin: 8px 0 0;
  color: var(--front-muted);
}

.footer-links {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.footer-link {
  padding: 10px 14px;
  border-radius: 999px;
  background: rgba(24, 37, 49, 0.06);
  transition: background 0.2s ease, color 0.2s ease;
}

.footer-link:hover {
  background: rgba(31, 116, 108, 0.12);
  color: var(--front-teal);
}

@media (max-width: 1180px) {
  .header-top {
    flex-direction: column;
    align-items: flex-start;
  }

  .account-block {
    justify-content: flex-start;
  }

  .nav-pill.utility {
    margin-left: 0;
  }
}

@media (max-width: 768px) {
  .site-shell {
    padding: 14px;
  }

  .site-footer {
    flex-direction: column;
    align-items: flex-start;
  }

  .brand-block,
  .account-block {
    width: 100%;
  }

  .account-block {
    justify-content: space-between;
  }
}
</style>
