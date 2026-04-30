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
    --front-bg: #f5f9ff;
    --front-paper: rgba(250, 253, 255, 0.96);
    --front-panel: #ffffff;
    --front-ink: #2f3440;
    --front-muted: #738198;
    --front-line: #d8e5f3;
    --front-accent: #4d8fd8;
    --front-accent-deep: #2367ad;
    --front-teal: #3f9eaa;
    --front-green: #4f8edb;
    --front-sky: #eaf4ff;
    --front-shadow: 0 18px 48px rgba(50, 73, 105, 0.12);
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
    font-family: "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;
    color: var(--front-ink);
    background:
      radial-gradient(circle at 12% 8%, rgba(77, 143, 216, 0.16), transparent 26%),
      radial-gradient(circle at 82% 10%, rgba(63, 158, 170, 0.12), transparent 24%),
      linear-gradient(145deg, #fbfdff 0%, #eef6ff 46%, #f7fbff 100%);
    background-attachment: fixed;
    overflow-x: hidden;
  }

  body::before {
    content: "";
    position: fixed;
    inset: 0;
    pointer-events: none;
    background-image:
      linear-gradient(rgba(35, 103, 173, 0.035) 1px, transparent 1px),
      linear-gradient(90deg, rgba(35, 103, 173, 0.035) 1px, transparent 1px);
    background-size: 28px 28px;
    mask-image: radial-gradient(circle at center, rgba(0, 0, 0, 0.75), transparent 92%);
    opacity: 0.42;
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
    border-color: var(--front-green) !important;
    background: linear-gradient(135deg, var(--front-green), var(--front-teal)) !important;
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
    background: linear-gradient(135deg, #fbfdff, #e8f2ff) !important;
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
    background: rgba(250, 253, 255, 0.88) !important;
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

  .list,
  .info,
  .list6,
  .order-page,
  .site-main > div > div:first-child,
  .center-preview .el-tabs__content,
  .center-preview .el-tabs__content .el-tab-pane {
    background:
      radial-gradient(circle at 12% 0, rgba(77, 143, 216, 0.1), transparent 28%),
      linear-gradient(145deg, rgba(250, 253, 255, 0.98), rgba(238, 246, 255, 0.94) 54%, rgba(247, 251, 255, 0.94)) !important;
  }

  .site-main > div > div:first-child {
    border-radius: 28px !important;
    box-shadow: var(--front-shadow);
  }

  .list-preview .list,
  .detail-preview .info,
  .order-page,
  .site-main > div > div:first-child,
  .section-content,
  .center-preview .el-tabs__content .el-tab-pane {
    border-color: rgba(35, 103, 173, 0.1) !important;
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

  .list-preview,
  .detail-preview,
  .center-preview {
    max-width: 1280px !important;
  }

  .breadcrumb-preview {
    max-width: 1280px !important;
    height: auto !important;
    margin: 0 auto 18px !important;
    padding: 0 24px !important;
  }

  .breadcrumb-preview .el-breadcrumb {
    line-height: 50px !important;
  }

  .list-preview .category-1 {
    width: 100% !important;
    margin: 0 auto 16px !important;
    padding: 16px 18px !important;
    justify-content: flex-start !important;
    gap: 10px;
    border: 1px solid rgba(36, 50, 66, 0.08);
    border-radius: 28px;
    background: rgba(250, 253, 255, 0.92) !important;
    box-shadow: var(--front-shadow);
  }

  .category-1 .item,
  .category-1 .item:hover,
  .category-1 .item.active {
    min-width: auto !important;
    height: auto !important;
    margin: 0 !important;
    padding: 0 18px !important;
    border: 1px solid rgba(36, 50, 66, 0.1) !important;
    border-radius: 999px !important;
    background: #fff !important;
    color: var(--front-ink) !important;
    line-height: 40px !important;
    box-shadow: none !important;
  }

  .category-1 .item:hover,
  .category-1 .item.active {
    border-color: rgba(47, 143, 103, 0.28) !important;
    background: rgba(47, 143, 103, 0.1) !important;
    color: var(--front-green) !important;
  }

  .list-preview .list-form-pv {
    width: 100% !important;
    margin: 0 auto !important;
    padding: 18px 20px !important;
    align-items: center !important;
    justify-content: flex-start !important;
    gap: 12px;
    border: 1px solid rgba(36, 50, 66, 0.08);
    border-radius: 30px;
    background: rgba(250, 253, 255, 0.92) !important;
    box-shadow: var(--front-shadow);
  }

  .list-preview .list-form-pv .el-form-item {
    margin: 0 !important;
  }

  .list-preview .list-form-pv .lable {
    padding: 0 8px 0 0 !important;
    color: var(--front-muted) !important;
    font-size: 14px !important;
    line-height: 40px !important;
  }

  .list-form-pv .el-input .el-input__inner,
  .list-form-pv .el-select .el-input__inner,
  .list-form-pv .el-date-editor .el-input__inner {
    width: 150px !important;
    border: 1px solid rgba(36, 50, 66, 0.12) !important;
    border-radius: 999px !important;
    background: #fff !important;
    box-shadow: none !important;
  }

  .list-preview .list {
    width: 100% !important;
    margin: 20px auto 14px !important;
    padding: 24px !important;
    border: 1px solid rgba(36, 50, 66, 0.08);
    border-radius: 34px;
    background: rgba(250, 253, 255, 0.95) !important;
    box-shadow: var(--front-shadow);
  }

  .list .index-pv1 {
    gap: 22px;
    margin: 0 !important;
    justify-content: flex-start !important;
  }

  .list .index-pv1 .list-item {
    width: calc(25% - 17px) !important;
    min-height: 0 !important;
    padding: 14px !important;
    border: 1px solid rgba(36, 50, 66, 0.08) !important;
    border-radius: 26px;
    background: #fff !important;
    overflow: hidden;
    box-shadow: 0 12px 30px rgba(36, 50, 66, 0.08);
    transition: transform 0.24s ease, box-shadow 0.24s ease;
  }

  .list .index-pv1 .list-item:hover {
    transform: translateY(-6px) !important;
    box-shadow: 0 20px 38px rgba(36, 50, 66, 0.14);
  }

  .list .index-pv1 .list-item .image {
    height: 220px !important;
    margin: 0 0 14px !important;
    padding: 14px;
    border-radius: 20px;
    background:
      linear-gradient(135deg, rgba(232, 242, 255, 0.9), rgba(246, 251, 255, 0.88));
  }

  .list .index-pv1 .list-item .price {
    padding: 0 4px 8px !important;
    color: var(--front-accent-deep) !important;
    text-align: left !important;
    font-size: 19px !important;
    font-weight: 800;
  }

  .list .index-pv1 .list-item .name {
    border: 0 !important;
    border-radius: 14px !important;
    background: #eef6ff !important;
    color: var(--front-ink) !important;
  }

  .detail-preview {
    margin: 24px auto 0 !important;
    padding: 0 0 24px !important;
  }

  .detail-preview .attr {
    gap: 22px;
    align-items: flex-start;
  }

  .detail-preview .attr > .el-carousel {
    width: calc(42% - 11px) !important;
    height: 520px !important;
    margin: 0 !important;
    padding: 0 !important;
  }

  .detail-preview .attr > .el-carousel .el-carousel__container,
  .detail-preview .attr > .el-carousel .el-carousel__item,
  .detail-preview .attr > .el-carousel .image {
    border-radius: 32px !important;
  }

  .detail-preview .info {
    order: 0 !important;
    width: calc(58% - 11px) !important;
    min-height: 520px !important;
    margin: 0 !important;
    padding: 26px !important;
    border: 1px solid rgba(36, 50, 66, 0.08) !important;
    border-radius: 34px !important;
    background: rgba(250, 253, 255, 0.96) !important;
  }

  .detail-preview .info .item {
    margin: 0 0 12px !important;
    padding: 12px 16px !important;
    border: 1px solid rgba(36, 50, 66, 0.08);
    border-radius: 18px;
    background: #fff !important;
    box-shadow: none !important;
  }

  .detail-preview .info .lable {
    min-width: 112px !important;
    color: var(--front-muted) !important;
    text-align: left !important;
  }

  .detail-preview .info .btn {
    gap: 10px;
    padding: 14px 0 0 !important;
  }

  .detail-preview .detail {
    margin-top: 22px;
    padding: 18px 24px !important;
    border-radius: 30px !important;
    background: rgba(250, 253, 255, 0.96) !important;
  }

  .center-preview {
    padding: 28px 0 80px !important;
  }

  .center-preview .title {
    margin: 0 0 18px !important;
    padding-left: 8px;
    color: var(--front-ink) !important;
    text-align: left !important;
    font-size: 30px !important;
  }

  .center-preview .el-tabs {
    align-items: stretch;
    gap: 24px;
  }

  .center-preview .el-tabs__header {
    width: 240px !important;
    height: auto !important;
    padding: 18px !important;
    border: 1px solid rgba(36, 50, 66, 0.08) !important;
    border-radius: 30px;
    background: rgba(250, 253, 255, 0.95) !important;
    box-shadow: var(--front-shadow);
  }

  .center-preview .el-tabs__item {
    display: block !important;
    width: 100% !important;
    min-width: 0 !important;
    margin: 0 0 10px !important;
    padding: 0 18px !important;
    border-radius: 999px !important;
    background: #fff !important;
    box-shadow: none !important;
    text-align: left !important;
  }

  .center-preview .el-tabs__item:hover,
  .center-preview .el-tabs__item.is-active {
    background: rgba(47, 143, 103, 0.12) !important;
    color: var(--front-green) !important;
  }

  .center-preview .el-tabs__content {
    flex: 1;
  }

  .center-preview .el-tabs__content .el-tab-pane {
    padding: 28px !important;
  }

  .center-preview-pv .el-form-item {
    border: 1px solid rgba(36, 50, 66, 0.08);
    border-radius: 20px;
    padding: 14px 18px !important;
    box-shadow: none !important;
  }

  @media (max-width: 1180px) {
    .list .index-pv1 .list-item {
      width: calc(33.333% - 15px) !important;
    }

    .detail-preview .attr > .el-carousel,
    .detail-preview .info {
      width: 100% !important;
    }
  }

  @media (max-width: 900px) {
    body::before {
      opacity: 0.25;
    }

    .el-dialog {
      width: calc(100% - 24px) !important;
      margin-top: 24px !important;
    }

    .list .index-pv1 .list-item {
      width: calc(50% - 11px) !important;
    }

    .center-preview .el-tabs {
      display: block !important;
    }

    .center-preview .el-tabs__header {
      width: 100% !important;
      margin-bottom: 18px !important;
    }
  }

  @media (max-width: 560px) {
    .list .index-pv1 .list-item {
      width: 100% !important;
    }

    .list-preview .list-form-pv {
      display: block !important;
    }

    .list-preview .list-form-pv .el-form-item,
    .list-form-pv .el-input .el-input__inner {
      width: 100% !important;
      margin-bottom: 10px !important;
    }
  }
</style>
