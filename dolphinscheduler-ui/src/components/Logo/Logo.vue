<template>
  <div class="logo">
    <span class="svg"></span>
    <span class="name">{{ name }}</span>
  </div>
</template>

<script lang="ts" setup>
  interface LogoProps {
    name?: string
  }

  withDefaults(defineProps<LogoProps>(), { name: '数据集成平台' })
</script>

<style lang="less">
  .logo {
    height: 100%;
    display: flex;
    font-size: 1.4rem;
    font-weight: 500;
    align-items: center;

    .svg {
      width: 36px;
      height: 36px;
      margin-right: 8px;
      background: url('@/assets/images/logo-data.svg') center no-repeat;
      background-size: 100% 100%;
    }
  }
</style>
