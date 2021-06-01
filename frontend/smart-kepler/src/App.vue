<template>
 <hooper :progress="true" :autoPlay="true" :playSpeed="20000">
    <slide>
      slide 1
    </slide>
    <slide>
      slide 2
    </slide>
  </hooper>
</template>

<script>
import { Hooper, Slide } from 'hooper';
import 'hooper/dist/hooper.css';
export default {
  name: 'App',
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
      
      //var convert = require('xml-js')
      let apiUrl = 'https://www.pervasive.jku.at/rss/rss.php?lva';
      console.log("Button pressed");
      let response = await this.axios.get(apiUrl);
      let xmlDoc = parser.parseFromString(response.data,"text/xml");
      this.news = xmlDoc; //.getElementsByTagName("title");
      //this.news = convert.xml2json(response.data);
      console.log(response.data);
    }
  }
}
</script>
<style>

</style>