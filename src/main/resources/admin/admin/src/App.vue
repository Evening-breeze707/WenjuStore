<template>
  <div id="app" class="">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "app",
};
</script>

<style lang="scss">
*{
  padding: 0;
  margin:0;
  box-sizing: border-box;
}
html,body{
  width: 100%;
  height: 100%;
  overflow: hidden;
}
#app{
  height:100%;
  overflow: hidden;
}
body {
  padding: 0;
  margin: 0;
  overflow: hidden;
}
</style>
