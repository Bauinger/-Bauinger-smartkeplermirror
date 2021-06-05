<template style="background: black;">
<span>
 <section v-if="errored">
        <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
 </section>
 <hooper :progress="true" :autoPlay="true" :playSpeed="20000" id="hooper">
    <slide v-bind:key="quote.quoteText" v-for="quote in quotes" class="slider">
      {{quote.quoteText}}
    </slide>
  </hooper>
  <h3 id="titleNews">Johannes Kepler</h3>
</span>
</template>

<script>
import { Hooper, Slide } from 'hooper';
import 'hooper/dist/hooper.css';
export default {
  name: 'Quotes',
  components: {
    Hooper,
    Slide,
  },
  data: function() {
    return {
      quotes : null,
      errored: false,
      timer : '',
    };
  },
  mounted() {
    this.loadQuotes();
    this.timer = setInterval(this.loadQuotes(), 3600000)
  },
  beforeDestroy (){
    this.cancelAutoUpdate();
  },
  methods: {
    async loadQuotes() {
      let apiUrl = 'http://localhost:8081/quote';
      let response = await this.axios.get(apiUrl)
                                     .catch(error => {
                                         console.log(error)
                                         this.errored = true;
                                       });
      this.quotes = response.data;
      console.log(response.data);
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