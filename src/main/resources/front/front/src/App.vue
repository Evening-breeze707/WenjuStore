<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  :root {
    --front-bg: #f4ecdf;
    --front-paper: rgba(255, 250, 241, 0.96);
    --front-panel: #fffdf8;
    --front-ink: #182531;
    --front-muted: #6a7682;
    --front-line: #d8ccb7;
    --front-accent: #d96f3a;
    --front-accent-deep: #b84d1f;
    --front-teal: #1f746c;
    --front-shadow: 0 20px 55px rgba(24, 37, 49, 0.12);
  }

  * {
    box-sizing: border-box;
  }

  html,
  body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
  }

  body {
    font-family: "Trebuchet MS", "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;
    color: var(--front-ink);
    background:
      radial-gradient(circle at top left, rgba(217, 111, 58, 0.12), transparent 28%),
      radial-gradient(circle at top right, rgba(31, 116, 108, 0.14), transparent 24%),
      linear-gradient(135deg, #f7f1e7 0%, #efe4d0 100%);
    background-attachment: fixed;
  }

  body::before {
    content: "";
    position: fixed;
    inset: 0;
    pointer-events: none;
    background-image:
      linear-gradient(rgba(24, 37, 49, 0.03) 1px, transparent 1px),
      linear-gradient(90deg, rgba(24, 37, 49, 0.03) 1px, transparent 1px);
    background-size: 28px 28px;
    mask-image: radial-gradient(circle at center, rgba(0, 0, 0, 0.75), transparent 92%);
    opacity: 0.55;
  }

  #app {
    min-height: 100%;
  }

  [v-cloak] {
    display: none;
  }

  .el-tabs__item {
    font-size: 18px;
  }

  .el-loading-spinner .el-loading-text {
    color: var(--front-teal);
  }

  .el-loading-spinner .path {
    stroke: var(--front-teal);
  }

  .el-breadcrumb {
    line-height: 60px;
    font-size: 18px;
  }

  .el-button {
    border-radius: 999px !important;
    font-weight: 700 !important;
    letter-spacing: 0.04em;
    transition: transform 0.2s ease, box-shadow 0.2s ease, opacity 0.2s ease;
  }

  .el-button:hover {
    transform: translateY(-1px);
    box-shadow: 0 10px 18px rgba(24, 37, 49, 0.16);
  }

  .el-button--primary {
    border-color: var(--front-teal) !important;
    background: linear-gradient(135deg, var(--front-teal), #2d9d92) !important;
    color: #fff !important;
  }

  .el-button--warning {
    border-color: var(--front-accent) !important;
    background: linear-gradient(135deg, var(--front-accent), var(--front-accent-deep)) !important;
    color: #fff !important;
  }

  .el-button--danger {
    border-color: #c65057 !important;
    background: linear-gradient(135deg, #d55f64, #b13c4a) !important;
    color: #fff !important;
  }

  .el-button--default,
  .el-button:not(.el-button--primary):not(.el-button--warning):not(.el-button--danger):not(.el-button--success) {
    border-color: rgba(24, 37, 49, 0.12) !important;
    background: rgba(255, 255, 255, 0.78) !important;
    color: var(--front-ink) !important;
  }

  .el-input__inner,
  .el-textarea__inner,
  .el-select .el-input__inner,
  .el-input-number .el-input__inner {
    border: 1px solid rgba(24, 37, 49, 0.12) !important;
    border-radius: 18px !important;
    background: rgba(255, 255, 255, 0.84) !important;
    box-shadow: none !important;
    color: var(--front-ink) !important;
  }

  .el-dialog,
  .el-dialog__body,
  .el-tabs--border-card,
  .el-table,
  .el-card,
  .el-popover {
    border-radius: 26px !important;
  }

  .el-dialog,
  .el-tabs--border-card,
  .el-card,
  .el-popover {
    border: 1px solid rgba(24, 37, 49, 0.08) !important;
    background: var(--front-paper) !important;
    box-shadow: var(--front-shadow) !important;
  }

  .el-table {
    overflow: hidden;
    border: 1px solid rgba(24, 37, 49, 0.08) !important;
    background: rgba(255, 255, 255, 0.86) !important;
    box-shadow: var(--front-shadow);
  }

  .el-table th {
    background: linear-gradient(135deg, #fffdf8, #ece0cc) !important;
    color: var(--front-ink) !important;
  }

  .el-table tr,
  .el-table td {
    background: transparent !important;
  }

  .el-table__body tr:hover > td {
    background: rgba(31, 116, 108, 0.08) !important;
  }

  .el-pagination {
    padding-bottom: 20px !important;
  }

  .el-pagination button,
  .el-pagination .el-pager li {
    border-radius: 999px !important;
  }

  .el-pagination .el-pager li.active {
    background: var(--front-teal) !important;
    color: #fff !important;
  }

  .breadcrumb-preview,
  .list-preview,
  .detail-preview,
  .center-preview,
  .section,
  .section-content,
  .home-preview,
  .news,
  .recommend,
  .main-content {
    position: relative;
    z-index: 1;
  }

  .breadcrumb-preview {
    border: 1px solid rgba(24, 37, 49, 0.08) !important;
    border-radius: 22px !important;
    background: rgba(255, 250, 241, 0.82) !important;
    box-shadow: var(--front-shadow);
  }

  .breadcrumb-preview .el-breadcrumb__inner,
  .breadcrumb-preview .el-breadcrumb__inner a,
  .breadcrumb-preview .el-breadcrumb__separator {
    color: var(--front-ink) !important;
  }

  .list-preview .list,
  .detail-preview .info,
  .detail-preview .detail,
  .center-preview .el-tabs__content .el-tab-pane,
  .section,
  .section-content {
    border: 1px solid rgba(24, 37, 49, 0.08) !important;
    border-radius: 30px !important;
    background: var(--front-paper) !important;
    box-shadow: var(--front-shadow);
  }

  .list-preview .category-1,
  .list-preview .list-form-pv,
  .detail-preview .attr,
  .center-preview .el-tabs__header,
  .section-title {
    background: transparent !important;
  }

  .center-preview .el-tabs__header .el-tabs__item {
    border-radius: 999px !important;
  }

  .list-preview .list,
  .detail-preview .info,
  .detail-preview .detail,
  .center-preview .el-tabs__content .el-tab-pane,
  .section-content,
  .home-preview .recommend,
  .home-preview .news {
    background: transparent !important;
  }

  .ql-editor {
    min-height: 220px;
  }

  .ql-snow .ql-tooltip[data-mode="link"]::before {
    content: "Link:";
  }

  .ql-snow .ql-tooltip.ql-editing a.ql-action::after {
    border-right: 0;
    content: "Save";
    padding-right: 0;
  }

  .ql-snow .ql-tooltip[data-mode="video"]::before {
    content: "Video:";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item::before {
    content: "14px";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
    content: "10px";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
    content: "18px";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
    content: "32px";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item::before {
    content: "Text";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
    content: "H1";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
    content: "H2";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
    content: "H3";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
    content: "H4";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
    content: "H5";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
    content: "H6";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item::before {
    content: "Sans";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
    content: "Serif";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
    content: "Mono";
  }

  @media (max-width: 900px) {
    body::before {
      opacity: 0.25;
    }

    .el-dialog {
      width: calc(100% - 24px) !important;
      margin-top: 24px !important;
    }
  }
</style>
