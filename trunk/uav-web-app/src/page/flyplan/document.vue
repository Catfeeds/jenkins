<template>
    <div>
        <el-table :data="files" style="width: 100%; margin-top:15px;">
            <el-table-column prop="name" label="资料名称"></el-table-column>
            <el-table-column prop="createTime" label="上传时间"></el-table-column>
            <el-table-column prop="updateTime" label="修改时间"></el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button type="primary" size="small">
                        <a :href="scope.row.url" style="display: block;width:100%;color:white;" target="_blank">下载</a>
                    </el-button>
                </template>
            </el-table-column>

        </el-table>
        <div class="center" style="margin-top:30px;">
            <el-pagination layout="prev, pager, next" :total="totalElements"
                           @current-change="fetchFiles" ></el-pagination>
        </div>
    </div>
</template>

<script>
    import urls from '../../components/urls'
    export default{
        methods: {
            fetchFiles(val){
                $.ajax({
                    type: 'GET',
                    url: urls.GET_FILEDOWN + '?page=' + val + '&size=5',
                    success: function (res) {
                    
                        this.files = res.data.content;
                        this.totalElements = res.data.totalElements;
                    }.bind(this),
                });
            }
        },
        data(){
            return {
                files: [],
                totalElements: 1
            }
        },
        mounted(){
            $.ajax({
                type: 'GET',
                url: urls.GET_FILEDOWN,
                success: function (res) {
                    this.files = res.data.content;
                    this.totalElements = res.data.totalElements;
                }.bind(this),

            });
        }
    }
</script>

<style lang="less">
    .myplan_sel .el-input__icon + .el-input__inner {
        width: 150px;
    }

</style>