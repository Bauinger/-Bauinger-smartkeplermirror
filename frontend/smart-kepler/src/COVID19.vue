<template>
  <span>
    <section v-if="errored">
            <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
    </section>
    <div class="display-6 fw-bold">COVID-19:</div>
    <div class="fs-4 fw-bold">Bestätigt:</div>
     <div class="fs-4">
       {{recovered.toLocaleString('de')}}
    </div>
    <div class="fs-4 fw-bold">Tote:</div>
     <div class="fs-4">
       {{dead.toLocaleString('de')}}
    </div>
    <div class="fs-4 fw-bold">Genesene:</div>
    <div class="fs-4">
       {{recovered.toLocaleString('de')}}
    </div>
  </span>
</template>

<script>
export default {
  name: 'COVID19',
  data: function() {
    return {
      recovered : '',
      confirmed: '',
      dead : '',
      errored: false,
      timer : '',
    };
  },
  mounted() {
    this.loadCovidArea();
    this.timer = setInterval(this.loadCovidArea, 3600000)
  },
  beforeDestroy (){
    this.cancelAutoUpdate();
  },
  methods: {
    async loadCovidArea() {
      let apiUrl = 'https://coronavirus-19-api.herokuapp.com/countries/austria';
      let res= await this.axios.get(apiUrl)
                               .catch(error => {
                                        console.log(error)
                                        this.errored = true;
                                     });
      let data = res.data;
      this.recovered = data.recovered;
      this.confirmed = data.cases;
      this.dead = data.deaths;
      console.log(data);
    },
    cancelAutoUpdate () {
      clearInterval(this.timer);
    }
  }
}
</script>
<style>
  #titleCOVID19 {
    text-align: left;
  }
</style>