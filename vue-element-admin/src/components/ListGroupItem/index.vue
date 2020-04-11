<template>
  <div class="list-group-item" :class="{ active: active }">
    <i v-if="icon" :class="icon" />{{ text }}
  </div>
</template>

<script>
export default {
  name: 'ListGroupItem',
  props: {
    text: {
      type: String,
      default: '',
      required: true
    },
    icon: {
      type: String,
      default: undefined,
      required: false
    },
    active: {
      type: Boolean,
      default: false,
      require: false
    }
  },
  data() {
    return {}
  }
}
</script>

<style>
  .list-group-item > i {
    margin-right: .25rem;
  }
</style>
