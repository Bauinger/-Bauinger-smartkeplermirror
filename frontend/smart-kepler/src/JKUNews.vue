<template style="background: black;">
<span>
 <section v-if="errored">
        <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
 </section>
 <hooper :progress="true" :autoPlay="true" :playSpeed="20000" id="hooper">
    <slide v-bind:key="newer.textContent" v-for="newer in news" class="slider">
      {{newer.textContent}}
    </slide>
  </hooper>
  <h3 id="titleNews">JKU News</h3>
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
      errored: false,
      timer : '',
    };
  },
  mounted() {
    this.loadNewsArea();
    this.timer = setInterval(this.loadNewsArea, 3600000)
  },
  beforeDestroy (){
    this.cancelAutoUpdate();
  },
  methods: {
    async loadNewsArea() {
      let parser = new DOMParser();
      let apiUrl = 'https://www.pervasive.jku.at/rss/rss.php?news&press&jobs&lva';
      let response = await this.axios.get(apiUrl)
                                     .catch(error => {
                                         console.log(error)
                                         this.errored = true;
                                       });
      let xmlDoc = parser.parseFromString(response.data,"text/xml");
      this.news = Array.from(xmlDoc.getElementsByTagName("title"));
      this.news.shift();
      console.log(this.news);
    },
    cancelAutoUpdate () {
      clearInterval(this.timer);
    }
  }
}
</script>
<style>
  #titleNews {
    text-align: center;
  }
 .slider{
   text-align: center;
   padding: 90px;
 }
</style>