<template style="background: black;">
<span>
 <section v-if="errored">
        <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
 </section>
 <hooper :progress="true" :autoPlay="true" :playSpeed="20000" id="hooper">
    <slide v-bind:key="quote.quoteText" v-for="quote in quotes" class="slider fs-3">
      {{quote.quoteText}}
    </slide>
  </hooper>
  <div class="text-center fw-bold fs-5">Johannes Kepler</div>
</span>
</template>

<script>
import { Hooper, Slide } from 'hooper';
import 'hooper/dist/hooper.css';
const Datastore = require('nedb-promises')
let db = Datastore.create('./db/quotes.db')
export default {
  name: 'Quotes',
  components: {
    Hooper,
    Slide,
  },
  data: function() {
    return {
      quotes : [],
      errored: false,
      timer : '',
    };
  },
  async mounted() {
    await this.setupDatabase();
    await this.loadQuotes();
    this.timer = setInterval(await this.loadQuotes(), 3600000)
  },
  beforeDestroy () {
    this.cancelAutoUpdate();
  },
  methods: {  
    async loadQuotes() {
      let doc = await db.find({})
                        .then(docs => {
                          return docs;
                        })
                        .catch(err => {console.log(err)});
      console.log(doc);
      this.quotes = doc;
    },
    async setupDatabase() {
      await db.remove({}, {multi: true})
        .catch( err => {
          console.log(err)
        });
      await db.insert({quoteText : '„Nur die Liebe zur Wahrheit schafft Wunder.“'});
      await db.insert({quoteText : '„Die Sterne sind nur der Vater deines Schicksals. Die Mutter ist deine eigene Seele.“'},
                  {quoteText : '„Hundert kleine Freuden sind tausendmal mehr wert als eine große.“'},
                  {quoteText : '„Die Welt ist ein körperliches Bild Gottes, der Geist ist ein unkörperliches, aber doch erschaffenes Bild Gottes.“'},
                  {quoteText : '„Mein höchster Wunsch ist, den Gott, den ich im Äußerem überall finde, auch innerlich, innerhalb meiner gleichermaßen gewahr zu werden.“'},
                  {quoteText : '„Es gibt nichts Wunderbareres, - nichts, was die Weisheit des Schöpfers bei den Verständigen heller bezeugt, als die Bewegung der fünf Planeten.“'},
                  {quoteText : '„Wenn es gar so dunkel ist in deinem Leben, sieh doch einmal nach, ob es nicht am Ende daher kommt, daß alle deine Fensterläden verschlossen sind.“'},
                  {quoteText : '„Das Beste findet sich dort, wo sich Fleiß mit Begabung verbindet.“'},
                  {quoteText : '„Die Geometrie gab es schon vor der Erschaffung der Welt. Sie ist ewig wie der Geist Gottes.“'},
                  {quoteText : '„Die Heilige Schrift, welche höhere Dinge lehrt, bedient sich der gewöhnlichen Redeweise, um verstanden zu werden.“'},
                  {quoteText : '„Täglich steht der Tod mir bevor, wohl weiß ich es sicher. Doch wenn ich schau, wie der Chor kreisender Sterne sich schlingt, fühl ich mich aufwärts gehoben, ich sitze an himmlischer Tafel.“'},
                  {quoteText : '„Ich wollte Theologe werden; lange war ich in Unruhe. Nun aber sehet, wie Gott durch mein Bemühen auch in der Astronomie gefeiert wird.“'},
                  {quoteText : '„Die Körper wären nicht schön, wenn sie sich nicht bewegten.“'},
                  {quoteText : '„Die Mathematik allein befriedigt den Geist durch ihre außerordentliche Gewissheit.“'},
                  {quoteText : '„Alles, was im Himmel ist oder geschieht, macht sich auf irgendeine verborgene Weise auf Erden und in der Natur bemerkbar.“'},
                  {quoteText : '„Ein Mangel an Phantasie bedeutet den Tod der Wissenschaft.“'},
                  {quoteText : '„Mir kommen die Wege, auf denen die Menschen zur Erkenntnis gelangen fast ebenso bewunderungswürdig vor wie die Natur der Dinge selbst.“'},
                  {quoteText : '„O wie der Mensch sich quält, o wie eitel ist das Treiben der Welt.“'},
                  {quoteText : '„Die Sterne können zwar die Seele eines Menschen erfüllen, den Erfolg selber aber vermögen sie nicht zu gewährleisten. Sie rütteln vielmehr den Menschen auf und stellen ihn gewissermaßen auf Wachposten, damit er die vorüberziehenden Gelegenheiten ergreift.“'},
        )
        .catch( err => {
          console.log(err)
        });
        console.log('Initialized Database!')
    },
    cancelAutoUpdate () {
      clearInterval(this.timer);
    }
  }
}
</script>
<style>

 .slider{
   text-align: center;
   padding: 90px;
 }
</style>