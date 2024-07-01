<template>
    <div>
        <h2>所有图书信息</h2>
        <hr>
        <div style="margin-bottom: 20px;">
            <el-input v-model="searchQuery" placeholder="请输入查询关键字" style="width: 200px; margin-right: 10px;"></el-input>
            <el-select v-model="searchType" placeholder="请选择查询类型" style="width: 200px; margin-right: 10px;">
                <el-option label="ISBN" value="ISBN"></el-option>
                <el-option label="书名" value="book_name"></el-option>
                <el-option label="作者" value="author"></el-option>
            </el-select>
            <el-button type="primary" @click="searchBooks">查询</el-button>
            <el-button type="primary" @click="resetSearch" style="margin-left: 10px;">重置搜索条件</el-button>
        </div>


        <el-button type="primary" @click="showAddBookDialog" class="addBook">添加图书</el-button>
        <el-table :data="tableData" height="600px" border style="width: 100%">
            <el-table-column prop="isbn" label="ISBN" width="180"></el-table-column>
            <el-table-column prop="book_name" label="书籍名称" width="180"></el-table-column>
            <el-table-column prop="publisher" label="出版社" width="180"></el-table-column>
            <el-table-column prop="author" label="作者" width="180"></el-table-column>
            <el-table-column prop="avai_num" label="可借数量" width="180"></el-table-column>
            <el-table-column prop="borrow_num" label="已借数量" width="180"></el-table-column>
            <el-table-column label="操作" width="180">
                <template slot-scope="scope">
                    <el-button @click="editBookInfo(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteBook(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 编辑/添加图书信息的对话框 -->
        <el-dialog title="图书信息" :visible.sync="dialogVisible" width="30%">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="ISBN" prop="ISBN" :label-width="formLabelWidth">
                    <el-input v-model="form.ISBN"></el-input>
                </el-form-item>
                <el-form-item label="书籍名称" prop="book_name" :label-width="formLabelWidth">
                    <el-input v-model="form.book_name"></el-input>
                </el-form-item>
                <el-form-item label="出版社" prop="publisher" :label-width="formLabelWidth">
                    <el-input v-model="form.publisher"></el-input>
                </el-form-item>
                <el-form-item label="作者" prop="author" :label-width="formLabelWidth">
                    <el-input v-model="form.author"></el-input>
                </el-form-item>
                <el-form-item label="可借数量" prop="avai_num" :label-width="formLabelWidth">
                    <el-input v-model="form.avai_num"></el-input>
                </el-form-item>
                <el-form-item label="已借数量" prop="borrow_num" :label-width="formLabelWidth">
                    <el-input v-model="form.borrow_num"></el-input>
                </el-form-item>
                <el-form-item label="是否可借" prop="can_borrow" :label-width="formLabelWidth">
                    <el-input v-model="form.can_borrow"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="submitForm">保存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios';
import { Message } from 'element-ui';
export default {
    data() {
        return {
            tableData: [],
            account:'',
            searchQuery: '',
            searchType: 'ISBN',
            dialogVisible: false,
            isEditMode: false,
            form: {
                ISBN: '',
                book_name: '',
                publisher: '',
                author: '',
                avai_num: 0,
                borrow_num: 0,
                can_borrow:0
            },
            formLabelWidth: '120px',
            rules: {
                ISBN: [{ required: true, message: '请输入ISBN', trigger: 'blur' }],
                book_name: [{ required: true, message: '请输入书籍名称', trigger: 'blur' }],
                publisher: [{ required: true, message: '请输入出版社', trigger: 'blur' }],
                author: [{ required: true, message: '请输入作者', trigger: 'blur' }],
                avai_num: [{ required: true, message: '请输入可借数量', trigger: 'blur' }],
                borrow_num: [{ required: true, message: '请输入已借数量', trigger: 'blur' }],
                can_borrow: [{ required: true, message: '请输入是否可借', trigger: 'blur' }]
            }
        }
    },
    mounted() {
      this.account = this.$route.params.account;
        this.fetchTableData();
    },
    methods: {
        fetchTableData() { // 获取所有数据
            axios.get('http://localhost:8080/book/all')
                .then(response => {
                    console.log(response.data);
                    this.tableData = response.data.data;
                    console.log(this.tableData);
                })
                .catch(error => {
                    console.error("Error fetching the table data:", error);
                });
            console.log('fetchTableData');
        },
        showAddBookDialog() {
            this.isEditMode = false;
            this.form = {
                ISBN: '',
                book_name: '',
                publisher: '',
                author: '',
                avai_num: 0,
                borrow_num: 0
            };
            this.dialogVisible = true;
        },
        editBookInfo(row) {
            this.isEditMode = true;
            this.form = { ...row };
            this.dialogVisible = true;
        },
        submitForm() {
          this.$refs.form.validate((valid) => {
            if (valid) {
              if (this.isEditMode) {
                // 调用更新图书信息的API
                const formData = new FormData()
                formData.append('ISBN', this.form.ISBN)
                formData.append('book_name', this.form.book_name)
                formData.append('publisher', this.form.publisher)
                formData.append('author', this.form.author)
                formData.append('avai_num', this.form.avai_num)
                formData.append('borrow_num', this.form.borrow_num)
                formData.append('can_borrow', this.form.can_borrow)
                formData.append('account', this.account)
                axios.post('http://localhost:8080/book/updatebook', formData, {
                  headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                  }
                })
                    .then(response => {
                      console.log(response.data);
                      this.message=response.data.message;
                      if (response.data.code === 0) {
                        this.dialogVisible = false;
                        this.fetchTableData();
                        Message.success(this.message);
                      }
                      else {
                        Message.error(this.message);
                      }
                    })
                    .catch(error => {
                      console.error("Error fetching the table data:", error);
                    });


              } else {
                // 调用添加图书信息的API
                const formData = new FormData()
                formData.append('ISBN', this.form.ISBN)
                formData.append('book_name', this.form.book_name)
                formData.append('publisher', this.form.publisher)
                formData.append('author', this.form.author)
                formData.append('avai_num', this.form.avai_num)
                formData.append('borrow_num', this.form.borrow_num)
                formData.append('can_borrow', this.form.can_borrow)
                formData.append('account', this.account)
                axios.post('http://localhost:8080/book/addbook', formData, {
                  headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                  }
                })
                    .then(response => {
                      console.log(response.data);
                      this.message=response.data.message;
                      if (response.data.code === 0) {
                        this.dialogVisible = false;
                        this.fetchTableData();
                        Message.success(this.message);
                      }
                      else {
                        Message.error(this.message);
                      }
                    })
                    .catch(error => {
                      console.error("Error fetching the table data:", error);
                    });



              }
              // 关闭弹出框
              this.dialogVisible = false;
            } else {
              console.error('表单验证失败');
              return false;
            }
          });
        },
        deleteBook(row) {
            console.log(row);
          this.form = { ...row };
          console.log(this.form.ISBN);
          axios.post('http://localhost:8080/book/deletebook?ISBN='+this.form.isbn+'&account='+this.account,{
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
              .then(response => {
                console.log(response);
                this.message=response.data.message;
                if (response.data.code === 0) {
                  this.dialogVisible = false;
                  this.fetchTableData();
                  Message.success(this.message);
                }
                else {
                  Message.error(this.message);
                }
              })
              .catch(error => {
                console.error("Error fetching the table data:", error);
              });

        },
        searchBooks() {
            if (!this.searchQuery) {
                this.$message({
                    message: '请输入查询关键字',
                    type: 'warning'
                });
                return;
            }
            // 根据 关键字 和 type 查询图书信息
            // 调用后端接口查询图书信息
            if (this.searchType === 'ISBN') {
                axios.get('http://localhost:8080/book/ISBN', {
                        params: {
                            ISBN: this.searchQuery
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
                    .catch(error => {
                        console.error("Error fetching the table data:", error);
                    });
            }
            else if (this.searchType === 'book_name') {
              axios.get('http://localhost:8080/book/name', {
                params: {
                  book_name: this.searchQuery
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
                  .catch(error => {
                    console.error("Error fetching the table data:", error);
                  });
            }
            else if (this.searchType === 'author') {
              axios.get('http://localhost:8080/book/author', {
                params: {
                  author: this.searchQuery
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
                  .catch(error => {
                    console.error("Error fetching the table data:", error);
                  });
            }
            this.tableData = [] // 后端的值
        },
        resetSearch() {
            this.searchQuery = '';
            this.searchType = 'ISBN';
            // 返回所有图书信息
            this.fetchTableData();
        }
    }
}
</script>


<style lang="scss" scoped>
.addBook {
    margin: 20px;
}
</style>