<template>
  <span>
    <Header></Header>
    <COVID19></COVID19>
    <JKUNews></JKUNews>
    <Quotes></Quotes>
  </span>
</template>

<script>
import JKUNews from './JKUNews';
import COVID19 from './COVID19';
import Header from './Header';
import Quotes from './Quotes';
export default {
  name: 'App',
  components: {
    JKUNews,
    Header,
    COVID19,
    Quotes
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