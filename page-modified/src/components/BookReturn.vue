<template>
    <div>
        <h2>你目前的借阅信息</h2>
        <el-table :data="tableData" height="auto" border style="width: 1005px;margin-bottom: 60px;">
            <el-table-column prop="isbn" label="ISBN" width="200"></el-table-column>
            <el-table-column prop="book_name" label="书籍名称" width="200"></el-table-column>
            <el-table-column prop="borrow_date" label="借书时间" width="200"></el-table-column>
            <el-table-column prop="due_date" label="截止还书时间" width="200"></el-table-column>
            <el-table-column prop="fine" label="罚款" width="200"></el-table-column>
        </el-table>

        <hr>
        <h2>还书</h2>
        <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="图书ISBN号">
                <el-input v-model="form.ISBN"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">确认还书</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            form: {
                ISBN: '',
            },
            tableData: []
        }
    },
    mounted() {
        this.fetchTableData();
    },
    methods: {
        fetchTableData() {
            // borrow_info 没有存图书名称
            // 这里后端需要根据ISBN返回图书名称
          axios.get('http://localhost:8080/borrow_info/getmy_info', {
            params: {
              card_num: this.$route.params.card_num
            }
          })
                .then(response => {
                  if (response.data.code === 0) {
                    this.$message({
                      message: response.data.message,
                      type: 'success'
                    });
                  } else{
                    this.$message({
                      message: response.data.message,
                      type: 'failed'
                    });
                  }
                  if (Array.isArray(response.data.data)) {
                    this.tableData = response.data.data;
                  } else {
                    this.tableData = [response.data.data];
                  }
                  console.log(this.$route.params.card_num);
                  console.log(response.data);
                })
        },
        onSubmit() {
            // 检查是否有空值
            if (this.form.ISBN === '') {
                this.$message({
                    message: '请填写完整信息',
                    type: 'warning'
                });
                return;
            }
            // 传给后端
            // to do
          axios.post('http://localhost:8080/book/return', {
            ISBN: this.form.ISBN,
            card_num: this.$route.params.card_num
          },{
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }
        )
          .then(response => {
            this.$message({
              message:response.data.data
            });
            console.log(this.form.ISBN);
            this.fetchTableData();
          })
        }
    }
}
</script>

<style lang="scss" scoped>

</style>