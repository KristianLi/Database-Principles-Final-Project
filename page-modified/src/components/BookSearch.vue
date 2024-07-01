ai_num<template>
    <div >
        <el-table :data="tableData" height="600px" border style="width: 100%">
            <el-table-column prop="ISBN" label="ISBN" width="200"></el-table-column>
            <el-table-column prop="book_name" label="书籍名称" width="200"></el-table-column>
            <el-table-column prop="publisher" label="出版社" width="200"></el-table-column>
            <el-table-column prop="author" label="作者" width="200"></el-table-column>
            <el-table-column prop="avai_num" label="可借数量" width="200"></el-table-column>
            <el-table-column prop="borrow_num" label="已借数量" width="200"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            tableData: [
                {
                    "ISBN": "9787121302954",
                    "book_name": "计算机网络",
                    "publisher": "清华大学出版社",
                    "author": "谢希仁",
                    "avai_num": 16,
                    "borrow_num": 0,
                    "can_borrow": 1
                },
                {
                    "ISBN": "9787040545180",
                    "book_name": "计算机组成原理",
                    "publisher": "高等教育出版社",
                    "author": "唐朔飞",
                    "avai_num": 20,
                    "borrow_num": 0,
                    "can_borrow": 1
                },
                {
                    "ISBN": "9787040492453",
                    "book_name": "操作系统原理、实现与实践",
                    "publisher": "高等教育出版社",
                    "author": "李治军",
                    "avai_num": 11,
                    "borrow_num": 0,
                    "can_borrow": 1
                }
            ]
        }
    },
    mounted() {
        this.fetchTableData();
    },
    methods: {
        fetchTableData() {
            axios.get('getbooks???') // 替换为后端接口
                .then(response => {
                    this.tableData = response.data;
                })
                .catch(error => {
                    console.error("Error fetching the table data:", error);
                });
        }
    }
}
</script>

<style lang="scss" scoped>

</style>
