<template>
  <div class="book-management">
    <h2>Book Management System</h2>
    <div v-if="reader">
      <h3>个人中心</h3>
      <p><strong>账号:</strong> {{ account }}</p>
      <p><strong>姓名:</strong> {{ reader.name }}</p>
      <p><strong>性别:</strong> {{ reader.gender }}</p>
      <p><strong>职称:</strong> {{ reader.title }}</p>
      <p><strong>可借书数:</strong> {{ reader.borrow_num }}</p>
      <p><strong>已借书数:</strong> {{ reader.borrowed_num }}</p>
      <p><strong>部门:</strong> {{ reader.department }}</p>
      <p><strong>电话:</strong> {{ reader.phone }}</p>
    </div>
    <div v-else>
      <p>加载中...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'BookManagement',
  data() {
    return {
      account: this.$route.params.account,
      password:this.$route.params.password,
      reader: null,
      errorMessage: ''
    };
  },
  created() {
    this.fetchReaderInfo();
  },
  methods: {
    async fetchReaderInfo() {
      try {
        const response = await axios.get(`http://localhost:8080/reader/info`, {
          params: {account: this.account,password:this.password}
        });
        if (response.data.code === 0) {
          this.reader = response.data.data;
        } else {
          this.errorMessage = `Failed to fetch reader info: ${response.data.message}`;
        }
      } catch (error) {
        console.error('There was an error!', error);
        this.errorMessage = 'An error occurred while fetching reader info.';
      }
    }
  }
};
</script>

<style scoped>
.book-management {
  max-width: 800px;
  margin: 0 auto;
}
</style>