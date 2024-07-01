<template>
    <div>
        <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="借书证号">
                <el-input v-model="form.cardnum"></el-input>
            </el-form-item>
            <el-form-item label="图书ISBN号">
                <el-input v-model="form.ISBN"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">确认借书</el-button>
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
            }
        }
    },
    methods: {
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
            axios.post(' http://localhost:8080/book/borrow', {
                ISBN: this.form.ISBN,
                card_num: this.form.cardnum
            },
                {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }
            )
            .then(response => {
                console.log(response);
                if (response.data.code === 0) {
                    this.$message({
                        message: response.data.message,
                        type: 'success'
                    });
                    return;
                }
                else if (response.data.code === 1) {
                    this.$message({
                        message: response.data.message,
                        type: 'warning'
                    });
                    return;
                }
                else {
                    this.$message({
                        message: '借书失败',
                        type: 'error'
                    });
                    return;
                }
            })
            .catch(error => {
                console.error("Error borrowing the book:", error);
                this.$message({
                    message: '借书失败',
                    type: 'error'
                });
            });
        }
    }
}
</script>

<style lang="scss" scoped>

</style>