<template>
  <div>
    <h1>Your payment was successfull!</h1>
    <v-btn text color="primary" @click="redirectToLU()">Return to LU</v-btn>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
        orderId : ''
    };
  },
  methods: {
   redirectToLU() {
      window.location = window.open("http://localhost:8084");
    }
  },
  mounted() {
    this.orderId = this.$route.params.id;
     axios
          .put("http://localhost:8082/orderRequest/edit/" + this.orderId + "/" + "SUCCESS")
          .then(() => {
            console.log("USPESNOO");

          })
          .catch((error) => {
            console.log(error);
          });
  }
};
</script>