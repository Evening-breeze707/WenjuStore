<template>
  <div class="menu-preview">
    <div class="aside-brand" @click="menuHandler('')">
      <div class="aside-mark">WS</div>
      <div>
        <p>后台系统</p>
        <strong>管理中心</strong>
      </div>
    </div>

    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu :default-openeds="[]" :unique-opened="true" class="el-menu-vertical-demo" default-active="0">
        <el-submenu index="0" @click.native="menuHandler('')">
          <template slot="title">
            <i class="el-icon-s-home" />
            <span>系统首页</span>
          </template>
        </el-submenu>

        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-user-solid" />
            <span>个人中心</span>
          </template>
          <el-menu-item index="1-1" @click="menuHandler('updatePassword')">修改密码</el-menu-item>
          <el-menu-item index="1-2" @click="menuHandler('center')">个人信息</el-menu-item>
        </el-submenu>

        <el-submenu v-for="(menu,index) in menuList.backMenu" :key="menu.menu" :index="index+2+''">
          <template slot="title">
            <i class="el-icon-menu" :class="icons[index]" />
            <span>{{ menu.menu }}</span>
          </template>
          <el-menu-item
            v-for="(child,sort) in menu.child"
            :key="sort"
            :index="(index+2)+'-'+sort"
            @click="menuHandler(child.tableName)"
          >
            {{ child.menu }}
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import menu from '@/utils/menu'

export default {
  data() {
    return {
      menuList: [],
      role: '',
      icons: [
        'el-icon-s-cooperation',
        'el-icon-s-order',
        'el-icon-s-platform',
        'el-icon-s-fold',
        'el-icon-s-unfold',
        'el-icon-s-operation',
        'el-icon-s-promotion',
        'el-icon-s-release',
        'el-icon-s-ticket',
        'el-icon-s-management',
        'el-icon-s-open',
        'el-icon-s-shop',
        'el-icon-s-marketing',
        'el-icon-s-flag',
        'el-icon-s-comment',
        'el-icon-s-finance',
        'el-icon-s-claim',
        'el-icon-s-custom',
        'el-icon-s-opportunity',
        'el-icon-s-data',
        'el-icon-s-check',
        'el-icon-s-grid',
        'el-icon-menu',
        'el-icon-chat-dot-square',
        'el-icon-message',
        'el-icon-postcard',
        'el-icon-position',
        'el-icon-microphone',
        'el-icon-close-notification',
        'el-icon-bangzhu',
        'el-icon-time',
        'el-icon-odometer',
        'el-icon-crop',
        'el-icon-aim',
        'el-icon-switch-button',
        'el-icon-full-screen',
        'el-icon-copy-document',
        'el-icon-mic',
        'el-icon-stopwatch',
      ]
    }
  },
  mounted() {
    const menus = menu.list()
    if (menus) {
      this.menuList = menus
    }
    this.role = this.$storage.get('role')
    for (let i = 0; i < this.menuList.length; i++) {
      if (this.menuList[i].roleName == this.role) {
        this.menuList = this.menuList[i]
        break
      }
    }
  },
  created() {
    this.icons.sort(() => {
      return (0.5 - Math.random())
    })
  },
  methods: {
    menuHandler(name) {
      this.$router.push('/' + name)
    }
  }
}
</script>

<style lang="scss" scoped>
.menu-preview {
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 18px 14px 18px 18px;
  background:
    radial-gradient(circle at top right, rgba(201, 106, 45, 0.14), transparent 22%),
    linear-gradient(180deg, rgba(255, 255, 255, 0.94), rgba(243, 247, 246, 0.96));
  border-right: 1px solid var(--admin-line);
  box-shadow: 12px 0 40px rgba(23, 48, 67, 0.06);
}

.aside-brand {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 16px 14px 20px;
  cursor: pointer;
}

.aside-mark {
  display: grid;
  place-items: center;
  width: 52px;
  height: 52px;
  border-radius: 18px;
  background: linear-gradient(135deg, var(--admin-accent), var(--admin-accent-deep));
  color: #fff;
  font-family: Georgia, "Times New Roman", serif;
  font-size: 24px;
  font-weight: 700;
}

.aside-brand p {
  margin: 0;
  color: var(--admin-muted);
  font-size: 11px;
  letter-spacing: 0.15em;
  text-transform: uppercase;
}

.aside-brand strong {
  display: block;
  margin-top: 6px;
  color: var(--admin-ink);
  font-family: Georgia, "Times New Roman", serif;
  font-size: 24px;
}

.menu-preview {
  .el-scrollbar {
    flex: 1 1 auto;
    min-height: 0;
    height: auto;

    & /deep/ .scrollbar-wrapper {
      overflow-x: hidden;
      overflow-y: auto !important;
    }

    .el-menu-vertical-demo {
      border: 0;
      background: transparent;
    }

    .el-menu-vertical-demo > .el-submenu /deep/ .el-submenu__title {
      margin-bottom: 8px;
      border: 0;
      border-radius: 18px;
      background: transparent;
      color: var(--admin-ink);
      font-weight: 700;
    }

    .el-menu-vertical-demo > .el-submenu /deep/ .el-submenu__title:hover,
    .el-menu-vertical-demo > .el-submenu.is-opened /deep/ .el-submenu__title {
      background: rgba(23, 48, 67, 0.08);
      color: var(--admin-accent-deep);
    }

    .el-menu-vertical-demo > .el-submenu /deep/ .el-submenu__title i {
      color: inherit;
      font-size: 18px;
    }

    .el-menu-vertical-demo .el-submenu /deep/ .el-menu {
      border: 0;
      background: transparent;
    }

    .el-menu-vertical-demo .el-submenu /deep/ .el-menu-item {
      margin: 6px 0 6px 14px;
      border-radius: 16px;
      background: rgba(255, 255, 255, 0.7);
      color: var(--admin-muted);
      text-align: left;
    }

    .el-menu-vertical-demo .el-submenu /deep/ .el-menu-item:hover,
    .el-menu-vertical-demo .el-submenu /deep/ .el-menu-item.is-active {
      background: linear-gradient(135deg, var(--admin-teal), #3f9f99);
      color: #fff;
    }
  }
}
</style>
