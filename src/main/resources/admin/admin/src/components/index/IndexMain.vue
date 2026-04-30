<template>
  <div class="admin-shell">
    <index-aside class="admin-aside" v-if="'vertical' == 'vertical'"></index-aside>
    <el-main class="admin-main">
      <div class="admin-glow admin-glow-a"></div>
      <div class="admin-glow admin-glow-b"></div>
      <index-header class="admin-header"></index-header>
      <bread-crumbs :title="title" class="admin-breadcrumb"></bread-crumbs>
      <div class="admin-stage">
        <router-view class="router-view"></router-view>
      </div>
    </el-main>
  </div>
</template>

<script>
import IndexAside from '@/components/index/IndexAsideStatic'
import IndexHeader from '@/components/index/IndexHeader'
import menu from "@/utils/menu";

export default {
  components: {
    IndexAside,
    IndexHeader
  },
  data() {
    return {
      menuList: [],
      role: "",
      currentIndex: -2,
      itemMenu: [],
      title: ''
    };
  },
  mounted() {
    let menus = menu.list();
    this.menuList = menus;
    this.role = this.$storage.get("role");
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      this.$nextTick(() => {})
    },
    menuHandler(menu) {
      this.$router.push({
        name: menu.tableName
      });
      this.title = menu.menu;
    },
    titleChange(index, menus) {
      this.currentIndex = index
      this.itemMenu = menus;
    },
    homeChange(index) {
      this.itemMenu = [];
      this.title = ""
      this.currentIndex = index
      this.$router.push({
        name: 'home'
      });
    },
    centerChange(index) {
      this.itemMenu = [{
        "buttons": ["鏂板", "鏌ョ湅", "淇敼", "鍒犻櫎"],
        "menu": "淇敼瀵嗙爜",
        "tableName": "updatePassword"
      }, {
        "buttons": ["鏂板", "鏌ョ湅", "淇敼", "鍒犻櫎"],
        "menu": "涓汉淇℃伅",
        "tableName": "center"
      }];
      this.title = ""
      this.currentIndex = index
      this.$router.push({
        name: 'home'
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.admin-shell {
  display: flex;
  height: 100vh;
  align-items: stretch;
  overflow: hidden;
}

.admin-aside {
  flex: 0 0 232px;
  width: 232px !important;
  min-width: 232px;
  height: 100vh;
  overflow: hidden;
  overscroll-behavior: contain;
}

.admin-main {
  flex: 1 1 auto;
  position: relative;
  height: 100vh;
  margin-left: 0 !important;
  padding: 14px 18px 18px 12px !important;
  background:
    radial-gradient(circle at 12% 8%, rgba(77, 143, 216, 0.16), transparent 30%),
    linear-gradient(135deg, #f5f9ff, #eef6ff 56%, #f8fbff);
  overflow-x: hidden;
  overflow-y: auto;
  overscroll-behavior: contain;
}

.admin-header,
.admin-breadcrumb {
  position: relative;
  z-index: 1;
}

.admin-breadcrumb {
  margin: 16px 0 0 !important;
}

.admin-stage {
  position: relative;
  z-index: 1;
  padding-top: 12px;
  padding-bottom: 20px;
}

.admin-glow {
  position: absolute;
  border-radius: 999px;
  filter: blur(18px);
  opacity: 0.6;
  pointer-events: none;
}

.admin-glow-a {
  top: -80px;
  right: -50px;
  width: 260px;
  height: 260px;
  background: rgba(77, 143, 216, 0.18);
}

.admin-glow-b {
  bottom: 6%;
  left: 8%;
  width: 280px;
  height: 280px;
  background: rgba(63, 158, 170, 0.14);
}

@media (max-width: 1100px) {
  .admin-aside {
    width: 208px !important;
    min-width: 208px;
    flex-basis: 208px;
  }

  .admin-main {
    margin-left: 0 !important;
    padding-left: 10px !important;
  }
}
</style>
