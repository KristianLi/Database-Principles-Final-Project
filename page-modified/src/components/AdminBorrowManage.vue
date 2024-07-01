<template>
    <div>
        <h2>所有借阅信息</h2>
        <hr>
        <div style="margin-bottom: 20px;">
            <el-input v-model="searchQuery" placeholder="请输入查询关键字" style="width: 200px; margin-right: 10px;"></el-input>
            <el-select v-model="searchType" placeholder="请选择查询类型" style="width: 200px; margin-right: 10px;">
                <el-option label="借书证号" value="card_num"></el-option>
                <el-option label="姓名" value="name"></el-option>
                <el-option label="部门" value="department"></el-option>
                <el-option label="手机号码" value="phone"></el-option>
            </el-select>
            <el-button type="primary" @click="searchBorrowInfo">查询</el-button>
            <el-button type="primary" @click="resetSearch" style="margin-left: 10px;">重置搜索条件</el-button>
        </div>
        <el-button type="primary" @click="showOverdueInfo" class="showInfo">显示所有逾期未还的信息</el-button>
        <el-table :data="tableData" height="auto" border style="width: 1405px;margin-bottom: 60px;">
            <el-table-column prop="card_num" label="借书证号" width="200"></el-table-column>
            <el-table-column prop="name" label="姓名" width="200"></el-table-column>
            <el-table-column prop="isbn" label="ISBN" width="200"></el-table-column>
            <el-table-column prop="book_name" label="书籍名称" width="200"></el-table-column>
            <el-table-column prop="borrow_date" label="借书时间" width="200"></el-table-column>
            <el-table-column prop="due_date" label="截止还书时间" width="200"></el-table-column>
            <el-table-column prop="fine" label="罚款" width="200"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
          account: '',
            tableData: [],
            searchQuery: '',
            searchType: 'card_num'
        }
    },
    mounted() {
      this.account=this.$route.params.account;
      this.fetchTableData();
    },
    methods: {
        fetchTableData() {
            // 后端接口
          axios.get('http://localhost:8080/borrow_info/all', {
            params: {
              account: this.account
            }
          })
              .then(response => {
                if (Array.isArray(response.data.data)) {
                  this.tableData = response.data.data;
                } else {
                  this.tableData = [response.data.data];
                }
                console.log(response.data);
              })
          console.log('fetchTableData');

        },
        searchBorrowInfo() {
            if (!this.searchQuery) {
                this.$message({
                    message: '请输入查询关键字',
                    type: 'warning'
                });
                return;
            }
            // 根据关键字和类型查询读者信息
           if (this.searchType === 'card_num') {
             axios.get('http://localhost:8080/borrow_info/askby_cardnum', {
               params: {
                 account: this.account,
                 card_num: this.searchQuery
               }
             })
                 .then(response => {
                   if (Array.isArray(response.data.data)) {
                     this.tableData = response.data.data;
                   } else {
                     this.tableData = [response.data.data];
                   }
                   console.log(response.data);
                 })
           } else if (this.searchType === 'name') {
             axios.get('http://localhost:8080/borrow_info/askby_name', {
               params: {
                 account: this.account,
                 name: this.searchQuery
               }
             })
                 .then(response => {
                   if (Array.isArray(response.data.data)) {
                     this.tableData = response.data.data;
                   } else {
                     this.tableData = [response.data.data];
                   }
                   console.log(response.data);
                 })
           } else if (this.searchType === 'department') {
             axios.get('http://localhost:8080/borrow_info/askby_department', {
               params: {
                 account: this.account,
                 department: this.searchQuery,
               }
             })
                 .then(response => {
                   if (Array.isArray(response.data.data)) {
                     this.tableData = response.data.data;
                   } else {
                     this.tableData = [response.data.data];
                   }
                   console.log(response.data);
                 })
           } else if (this.searchType === 'phone') {
             axios.get('http://localhost:8080/borrow_info/askby_phone', {
               params: {
                 account: this.account,
                 phone: this.searchQuery
               }
             })
                 .then(response => {
                   if (Array.isArray(response.data.data)) {
                     this.tableData = response.data.data;
                   } else {
                     this.tableData = [response.data.data];
                   }
                   console.log(response.data);
                 })
           }
            console.log('searchBorrowInfo');
        },
        resetSearch() {
            this.searchQuery = '';
            this.searchType = 'card_num';
            // 重置 显示的数据 为 所有读者信息
            this.fetchTableData();
        },
        showOverdueInfo() {
            // 显示所有逾期未还的信息
            // 后端返回 due_date（预定的还书时间） < 当前日期 的信息
          axios.get('http://localhost:8080/borrow_info/outdate', {
            params: {
              account: this.account
            }
          })
              .then(response => {
                if (Array.isArray(response.data.data)) {
                  this.tableData = response.data.data;
                } else {
                  this.tableData = [response.data.data];
                }
                console.log(response.data);
              })
        }
    }
}
</script>

<style lang="scss" scoped>
.showInfo {
    margin: 20px;
}
</style>