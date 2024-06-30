<template>
  <div class="login">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <div>
        <label for="account">Username:</label>
        <input type="text" v-model="account" id="account" required>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" v-model="password" id="password" required>
      </div>
      <button type="submit">Login</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserLogin',
  data() {
    return {
      account: '',
      password: '',
      message: ''
    };
  },
  methods: {
    async login() {
      const formData = new FormData();
      formData.append('account', this.account);
      formData.append('password', this.password);

      try {
        const response = await axios.post('http://localhost:8080/user/login', formData, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        });
        if (response.data.code === 0) {
          this.message = response.data.data; // 显示"登陆成功"
          const account=this.account;
          const password=this.password;
          this.$router.push(`/book-management/${account}/${password}`); // 跳转到图书管理系统页面
        } else {
          this.message = `Login failed: ${response.data.message}`;
        }
      } catch (error) {
        console.error('There was an error!', error);
        this.message = 'An error occurred during login.';
      }
    }
  }
};
</script>

<style scoped>
.login {
  max-width: 300px;
  margin: 0 auto;
}
</style>