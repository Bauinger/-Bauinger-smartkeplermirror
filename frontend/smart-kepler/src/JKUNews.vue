<template style="background: black;">
<span>
 <hooper :progress="true" :autoPlay="true" :playSpeed="20000" id="hooper">
    <slide v-bind:key="newer.textContent" v-for="newer in news" class="slider">
      {{newer.textContent}}
    </slide>
  </hooper>
  <h3 id="title">JKU News</h3>
</span>
</template>

<script>
import { Hooper, Slide } from 'hooper';
import 'hooper/dist/hooper.css';
export default {
  name: 'JKUNews',
  components: {
    Hooper,
    Slide,
  },
  data: function() {
    return {
      news : null,
    };
  },
  mounted() {
    this.loadNewsArea();
  },
  methods: {
    async loadNewsArea() {
      let parser = new DOMParser();
      let apiUrl = 'https://www.pervasive.jku.at/rss/rss.php?news&press&jobs&lva';
      console.log("Button pressed");
      let response = await this.axios.get(apiUrl);
      let xmlDoc = parser.parseFromString(response.data,"text/xml");
      this.news = Array.from(xmlDoc.getElementsByTagName("title"));
      this.news.shift();
      console.log(this.news);
    }
  }
}
</script>
<style>
  #title {
    text-align: center;
  }
 .slider{
   text-align: center;
   padding: 90px;
 }
</style>