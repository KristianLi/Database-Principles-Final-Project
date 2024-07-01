<template>
  <div class="book-management">
    <h2>Book Management System</h2>
    <div v-if="userInfo">
      <h3>个人中心</h3>
      <p><strong>账号：</strong>{{ userInfo.account }}</p>
      <p><strong>姓名：</strong>{{ userInfo.name }}</p>
      <p><strong>性别：</strong>{{ userInfo.gender }}</p>
      <p><strong>职位：</strong>{{ userInfo.title }}</p>
      <p><strong>部门：</strong>{{ userInfo.department }}</p>
      <p><strong>电话：</strong>{{ userInfo.phone }}</p>
      <h3>借阅的书籍</h3>
      <ul>
        <li v-for="book in borrowedBooks" :key="book.ISBN">
          <p><strong>书名：</strong>{{ book.book_name }}</p>
          <p><strong>作者：</strong>{{ book.author }}</p>
          <p><strong>出版社：</strong>{{ book.publisher }}</p>
          <p><strong>借阅日期：</strong>{{ book.borrow_date }}</p>
          <p><strong>到期日期：</strong>{{ book.due_date }}</p>
        </li>
      </ul>
    </div>
    <p v-else>Loading...</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'BookManagement',
  data() {
    return {
      userInfo: null,
      borrowedBooks: []
    };
  },
  async created() {
    // 假设你已经在登录成功后保存了用户的账号信息
    const account = this.$route.query.account;

    // 获取用户信息
    try {
      const userResponse = await axios.get(`http://localhost:8080/user/info?account=${account}`);
      this.userInfo = userResponse.data;

      // 获取用户借阅的书籍信息
      const booksResponse = await axios.get(`http://localhost:8080/user/borrowed-books?card_num=${this.userInfo.card_num}`);
      this.borrowedBooks = booksResponse.data;
    } catch (error) {
      console.error('There was an error fetching the user info or borrowed books', error);
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