<template>
    <div>
        <h2>你目前的借阅信息</h2>
        <el-table :data="tableData" height="auto" border style="width: 1005px;margin-bottom: 60px;">
            <el-table-column prop="ISBN" label="ISBN" width="200"></el-table-column>
            <el-table-column prop="book_name" label="书籍名称" width="200"></el-table-column>
            <el-table-column prop="borrow_date" label="借书时间" width="200"></el-table-column>
            <el-table-column prop="due_date" label="截止还书时间" width="200"></el-table-column>
            <el-table-column prop="fine" label="罚款" width="200"></el-table-column>
        </el-table>

        <hr>
        <h2>还书</h2>
        <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="你的借书证号">
                <el-input v-model="form.cardnum"></el-input>
            </el-form-item>
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
                cardnum: '',
                ISBN: '',
            },
            tableData: [
                {
                    "ISBN": "9787121302954",
                    "book_name": "计算机网络",
                    "borrow_date": "2023-12-01",
                    "due_date": "2024-01-01",
                    "fine": 0,
                }
            ]
        }
    },
    mounted() {
        this.fetchTableData();
    },
    methods: {
        fetchTableData() {
            // borrow_info 没有存图书名称
            // 这里后端需要根据ISBN返回图书名称
            axios.get('getbooks???') // 替换为后端接口
                .then(response => {
                    this.tableData = response.data;
                })
                .catch(error => {
                    console.error("Error fetching the table data:", error);
                });
        },
        onSubmit() {
            // 检查是否有空值
            if (this.form.cardnum === '' || this.form.ISBN === '') {
                this.$message({
                    message: '请填写完整信息',
                    type: 'warning'
                });
                return;
            }
            // 传给后端
            // to do
        }
    }
}
</script>

<style lang="scss" scoped>

</style>