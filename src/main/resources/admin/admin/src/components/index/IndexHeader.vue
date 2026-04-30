<template>
  <div class="navbar">
    <div class="header-brand">
      <div class="brand-mark">WS</div>
      <div class="brand-copy">
        <p>管理控制台</p>
        <span>{{ this.$project.projectName }}</span>
      </div>
    </div>

    <div class="header-status">
      <div class="status-card">
        <span>当前角色</span>
        <strong>{{this.$storage.get('role')}}</strong>
      </div>
      <div class="status-card">
        <span>登录账号</span>
        <strong>{{this.$storage.get('adminName')}}</strong>
      </div>
      <button v-if="this.$storage.get('role')!='管理员'" class="header-link" @click="onIndexTap">前台</button>
      <button class="header-link danger" @click="onLogout">退出登录</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      ruleForm: {},
      user: {},
    };
  },
  mounted() {
    let sessionTable = this.$storage.get("sessionTable")
    this.$http({
      url: sessionTable + '/session',
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.user = data.data;
        this.$storage.set('userid', data.data.id);
      } else {
        this.$message.error(data.msg);
      }
    });
  },
  methods: {
    onLogout() {
      let storage = this.$storage
      let router = this.$router
      storage.clear()
      router.replace({
        name: "login"
      });
    },
    onIndexTap() {
      window.location.href = `${this.$base.indexUrl}`
    }
  }
};
</script>

<style lang="scss" scoped>
.navbar {
  position: sticky;
  top: 0;
  z-index: 30;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 18px;
  padding: 18px 22px;
  border: 1px solid var(--admin-line);
  border-radius: 30px;
  background: rgba(255, 255, 255, 0.88);
  box-shadow: var(--admin-shadow);
}

.header-brand {
  display: flex;
  align-items: center;
  gap: 14px;
  min-width: 0;
}

.brand-mark {
  display: grid;
  place-items: center;
  width: 56px;
  height: 56px;
  border-radius: 18px;
  background: linear-gradient(135deg, var(--admin-accent), var(--admin-teal));
  color: #fff;
  font-family: Georgia, "Times New Roman", serif;
  font-size: 26px;
  font-weight: 700;
}

.brand-copy p {
  margin: 0;
  color: var(--admin-muted);
  font-size: 11px;
  letter-spacing: 0.16em;
  text-transform: uppercase;
}

.brand-copy span {
  display: block;
  margin-top: 6px;
  font-family: Georgia, "Times New Roman", serif;
  font-size: 28px;
  color: var(--admin-ink);
}

.header-status {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 10px;
  flex-wrap: wrap;
}

.status-card {
  min-width: 110px;
  padding: 10px 14px;
  border-radius: 18px;
  background: rgba(77, 143, 216, 0.08);
}

.status-card span {
  display: block;
  color: var(--admin-muted);
  font-size: 10px;
  letter-spacing: 0.14em;
  text-transform: uppercase;
}

.status-card strong {
  display: block;
  margin-top: 4px;
  color: var(--admin-ink);
}

.header-link {
  border: 0;
  padding: 12px 16px;
  border-radius: 999px;
  background: rgba(77, 143, 216, 0.12);
  color: var(--admin-accent-deep);
  cursor: pointer;
  font: inherit;
  font-weight: 700;
}

.header-link.danger {
  background: rgba(35, 103, 173, 0.1);
  color: var(--admin-accent-deep);
}

@media (max-width: 900px) {
  .navbar {
    flex-direction: column;
    align-items: flex-start;
  }

  .header-status {
    width: 100%;
    justify-content: flex-start;
  }
}
</style>
