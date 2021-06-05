<template>
 <div class="text-white m-4">
  <div class="align-top">
    <Header></Header>
  </div>
  <div style="margin-top: 100px !important">
    <COVID19></COVID19>
  </div>
  <div style="margin-top: 50px !important">
    <JKUNews></JKUNews>
  </div>
  <div style="margin-top: 50px !important">
    <Quotes></Quotes>
  </div>
</div>
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