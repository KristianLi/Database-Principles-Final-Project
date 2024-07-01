<template>
    <div>
        <h2>所有读者信息</h2>
        <hr>
        <div style="margin-bottom: 20px;">
            <el-input v-model="searchQuery" placeholder="请输入查询关键字" style="width: 200px; margin-right: 10px;"></el-input>
            <el-select v-model="searchType" placeholder="请选择查询类型" style="width: 200px; margin-right: 10px;">
                <el-option label="借书证号" value="card_num"></el-option>
                <el-option label="姓名" value="name"></el-option>
                <el-option label="部门" value="department"></el-option>
                <el-option label="手机号码" value="phone"></el-option>
            </el-select>
            <el-button type="primary" @click="searchReaders">查询</el-button>
            <el-button type="primary" @click="resetSearch" style="margin-left: 10px;">重置搜索条件</el-button>
        </div>
        <el-button type="primary" @click="showAddReaderDialog" class="addReader">添加读者</el-button>
        <el-table :data="tableData" height="600px" border style="width: 100%">
            <el-table-column prop="card_num" label="借书证号" width="180"></el-table-column>
            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="gender" label="性别" width="180"></el-table-column>
            <el-table-column prop="title" label="职称" width="180"></el-table-column>
            <el-table-column prop="borrow_num" label="可借数量" width="180"></el-table-column>
            <el-table-column prop="borrowed_num" label="已借数量" width="180"></el-table-column>
            <el-table-column prop="department" label="部门" width="180"></el-table-column>
            <el-table-column prop="phone" label="手机号码" width="180"></el-table-column>
            <el-table-column label="操作" width="180">
                <template slot-scope="scope">
                    <el-button @click="editReaderInfo(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteReader(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 添加/编辑读者信息的对话框 -->
        <el-dialog title="读者信息" :visible.sync="dialogVisible" width="30%">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="借书证号" prop="card_num" :label-width="formLabelWidth">
                    <el-input v-model="form.card_num" :disabled="isEditMode"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender" :label-width="formLabelWidth">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="男">男</el-radio>
                        <el-radio label="女">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="职称" prop="title" :label-width="formLabelWidth">
                    <el-input v-model="form.title"></el-input>
                </el-form-item>
                <el-form-item label="可借数量" prop="borrow_num" :label-width="formLabelWidth">
                    <el-input v-model.number="form.borrow_num"></el-input>
                </el-form-item>
                <el-form-item label="已借数量" prop="borrowed_num" :label-width="formLabelWidth">
                    <el-input v-model.number="form.borrowed_num"></el-input>
                </el-form-item>
                <el-form-item label="部门" prop="department" :label-width="formLabelWidth">
                    <el-input v-model="form.department"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone" :label-width="formLabelWidth">
                    <el-input v-model="form.phone"></el-input>
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
// import axios from 'axios';

import axios from "axios";
import {Message} from "element-ui";

export default {
    data() {
        return {
            tableData: [],
            account:'',
            searchQuery: '',
            searchType: 'card_num',
            dialogVisible: false,
            isEditMode: false,
            form: {
                card_num: '',
                name: '',
                gender: '男',
                title: '',
                borrow_num: 0,
                borrowed_num: 0,
                department: '',
                phone: ''
            },
            formLabelWidth: '120px',
            rules: {
                card_num: [{ required: true, message: '请输入借书证号', trigger: 'blur' }],
                name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
                gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
                title: [{ required: true, message: '请输入职称', trigger: 'blur' }],
                borrow_num: [{ required: true, message: '请输入可借数量', trigger: 'blur' }],
                borrowed_num: [{ required: true, message: '请输入已借数量', trigger: 'blur' }],
                department: [{ required: true, message: '请输入部门', trigger: 'blur' }],
                phone: [
                    { required: true, message: '请输入手机号码', trigger: 'blur' },
                ]
            }
        }
    },
    mounted() {
      this.account = this.$route.params.account;
      this.fetchTableData();
    },
    methods: {
      fetchTableData() { // 获取所有数据
        axios.get('http://localhost:8080/reader/info')
            .then(response => {
              console.log(response.data);
              this.tableData = response.data.data;
            })
            .catch(error => {
              console.error("Error fetching the table data:", error);
            });
        console.log('fetchTableData');
        },
        showAddReaderDialog() {
            this.isEditMode = false;
            this.resetForm();
            this.dialogVisible = true;
        },
        editReaderInfo(row) {
            this.isEditMode = true;
            this.form = { ...row };
            this.dialogVisible = true;
        },
        submitForm() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    if (this.isEditMode) {
                        // 编辑模式下，调用更新读者信息的 API
                        console.log('提交的表单数据（编辑模式）：', this.form);
                      const formData = new FormData()
                      console.log(this.account);
                      formData.append('account', this.account)
                      formData.append('card_num', this.form.card_num)
                      formData.append('name', this.form.name)
                      formData.append('gender',this.form.gender)
                      formData.append('title', this.form.title)
                      formData.append('borrow_num', this.form.borrow_num)
                      formData.append('borrowed_num', this.form.borrowed_num)
                      formData.append('department', this.form.department)
                      formData.append('phone', this.form.phone)
                      axios.post('http://localhost:8080/reader/update', formData, {
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
                        // 添加模式下，调用添加读者信息的 API
                      let formData = new FormData()
                      formData.append('account', this.account)
                      formData.append('card_num', this.form.card_num)
                      formData.append('name', this.form.name)
                      formData.append('gender',this.form.gender)
                      formData.append('title', this.form.title)
                      formData.append('borrow_num', this.form.borrow_num)
                      formData.append('borrowed_num', this.form.borrowed_num)
                      formData.append('department', this.form.department)
                      formData.append('phone', this.form.phone)
                      formData.forEach((value, key) => {
                        console.log(`${key}: ${value}`);
                      });
                      axios.post('http://localhost:8080/reader/add', formData, {
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
                    this.dialogVisible = false; // 关闭对话框
                } else {
                    console.error('表单验证失败');
                    return false;
                }
            });
        },
        deleteReader(row) {
            // 打印删除的读者的借书证号
            console.log(row.card_num);
            // 根据row.card_num调用后台API
          const formData = new FormData()
          console.log(this.account);
          formData.append('account', this.account)
          formData.append('card_num', row.card_num)
          axios.post('http://localhost:8080/reader/delete', formData, {
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

        },
        searchReaders() {
          if (!this.searchQuery) {
            this.$message({
              message: '请输入查询关键字',
              type: 'warning'
            });
            return;
          }
          // 根据 关键字 和 type 查询图书信息
          // 调用后端接口查询图书信息
          if (this.searchType === 'card_num') {
            axios.get('http://localhost:8080/reader/card_num', {
              params: {
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
                .catch(error => {
                  console.error("Error fetching the table data:", error);
                });
          }
          else if (this.searchType === 'name') {
            axios.get('http://localhost:8080/reader/name', {
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
          else if (this.searchType === 'department') {
            axios.get('http://localhost:8080/reader/department', {
              params: {
                department: this.searchQuery
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
          else if (this.searchType === 'phone') {
            axios.get('http://localhost:8080/reader/phone', {
              params: {
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
                .catch(error => {
                  console.error("Error fetching the table data:", error);
                });
          }
          this.tableData = [] // 后端的值
        },
        resetSearch() {
            this.searchQuery = '';
            this.searchType = 'card_num';
            // 重置 显示的数据 为 所有读者信息
            this.fetchTableData();
        },
        resetForm() {
            this.form = {
                card_num: '',
                name: '',
                gender: '男',
                title: '',
                borrow_num: 10,
                borrowed_num: 0,
                department: '',
                phone: ''
            };
        }
    }
}
</script>

<style lang="scss" scoped>
.addReader {
    margin: 20px;
}
</style>
