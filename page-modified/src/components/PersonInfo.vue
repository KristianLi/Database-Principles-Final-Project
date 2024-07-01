<template>
    <div class="user-info">
        <p>个人信息</p>
        <p>账号: {{ account }}</p>
        <p>借书证号: {{ card_num }}</p>
        <p>姓名: {{ name }}</p>
        <p>性别: {{ gender }}</p>
        <p>职称: {{ title }}</p>
    </div>
</template>

<script>

import axios from "axios";

export default {
    data() {
        return {
          datatable:[],
            account: '',
            card_num: '',
            name: '',
            gender: '',
            title: '',
        };
    },
  created() {
    this.account = this.$route.params.account;
    this.card_num = this.$route.params.card_num;
    axios.get('http://localhost:8080/reader/card_num', {
      params: {
        card_num: this.card_num
      }
    })
        .then(response => {
          console.log(response.data);
          this.datatable = response.data.data;
          this.name = this.datatable[0].name;
          this.gender = this.datatable[0].gender;
          this.title = this.datatable[0].title;
        })
  }
}
</script>

<style lang="scss" scoped>
.user-info {
    p {
        margin: 0.5em 0;
        font-size: 1em;
        color: #333;
    }

    p:first-of-type {
        font-weight: bold;
        font-size: 1.2em;
    }
}
</style>
