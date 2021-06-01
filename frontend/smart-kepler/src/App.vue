<template>
  <span>
    <Header></Header>
    <COVID19></COVID19>
    <JKUNews></JKUNews>
  </span>
</template>

<script>
import JKUNews from './JKUNews';
import COVID19 from './COVID19';
import Header from './Header';
export default {
  name: 'App',
  components: {
    JKUNews,
    Header,
    COVID19
  },
  data: function() {
    return {
    };
  },
  mounted() {
  },
  methods: {
  }
}
</script>