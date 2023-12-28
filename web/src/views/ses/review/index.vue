<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">评价星级</label>
        <el-input v-model="query.star" clearable placeholder="评价星级" style="width: 160px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">评价用户</label>
        <el-input v-model="query.userId" clearable placeholder="评价用户" style="width: 160px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">评价商品</label>
        <el-input v-model="query.productId" clearable placeholder="评价商品" style="width: 160px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">评价商铺</label>
        <el-input v-model="query.shopId" clearable placeholder="评价商铺" style="width: 160px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>

      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="评价内容" prop="contents">
            <el-input v-model="form.contents" :rows="3" type="textarea" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="评价星级" prop="star">
            <el-input v-model="form.star" style="width: 370px;" />
          </el-form-item>
          <!--          <el-form-item label="评价用户">-->
          <!--            <el-input v-model="form.userId" style="width: 370px;" />-->
          <!--          </el-form-item>-->
          <el-form-item label="评价用户">
            <el-select v-model="form.userId" placeholder="请选择">
              <el-option
                v-for="item in userList"
                :key="item.userId"
                :label="item.username"
                :value="item.userId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="评价商品">
            <el-select v-model="form.productId" placeholder="请选择">
              <el-option
                v-for="item in productList"
                :key="item.productId"
                :label="item.title"
                :value="item.productId"
              />
            </el-select>
          </el-form-item>
          <!--          <el-form-item label="评价商品">-->
          <!--            <el-input v-model="form.productId" style="width: 370px;" />-->
          <!--          </el-form-item>-->
          <el-form-item label="评价商铺">
            <el-select v-model="form.shopId" placeholder="请选择">
              <el-option
                v-for="item in shopList"
                :key="item.shopId"
                :label="item.title"
                :value="item.shopId"
              />
            </el-select>
          </el-form-item>
          <!--          <el-form-item label="评价商铺">-->
          <!--            <el-input v-model="form.shopId" style="width: 370px;" />-->
          <!--          </el-form-item>-->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="reviewId" label="评价编号" />
        <el-table-column prop="contents" label="评价内容" />
        <el-table-column prop="star" label="评价星级" />
        <el-table-column prop="userId" label="评价用户" />
        <el-table-column :show-overflow-tooltip="true" prop="product" label="评价商品">
          <template slot-scope="scope">
            <div>{{ scope.row.product.title }}</div>
          </template>
        </el-table-column>
        <!--        <el-table-column prop="productId" label="评价商品" />-->
        <el-table-column prop="shopId" label="评价商铺" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column v-if="checkPer(['admin','review:edit','review:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudReview from '@/api/review'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import { getProduct } from '@/api/ses/product'
import { getShop } from '@/api/ses/shop'
import { getUser } from '@/api/system/user'

const defaultForm = { reviewId: null, contents: null, star: null, productId: null, userId: null, shopId: null, createTime: null, product: { product_id: null }, shop: { shop_id: null }, user: { user_id: null }}
export default {
  name: 'Review',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '评价', url: 'api/review', idField: 'reviewId', sort: 'reviewId,desc', crudMethod: { ...crudReview }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'review:add'],
        edit: ['admin', 'review:edit'],
        del: ['admin', 'review:del']
      },
      rules: {
        contents: [
          { required: true, message: '评价内容不能为空', trigger: 'blur' }
        ],
        star: [
          { required: true, message: '评价星级不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'star', display_name: '评价星级' },
        { key: 'userId', display_name: '评价用户' },
        { key: 'productId', display_name: '评价商品' },
        { key: 'shopId', display_name: '评价商铺' }
      ]
    }
  },
  created() {
    this.getProduct()
    this.getShop()
    this.getUser()
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      this.getProduct()
      this.getShop()
      this.getUser()
      return true
    },
    async getProduct() {
      const productList = await getProduct()
      console.log(productList)
      this.productList = productList.content
    },
    async getShop() {
      const shopList = await getShop()
      // console.log(shopList)
      this.shopList = shopList.content
    },
    async getUser() {
      const userList = await getUser()
      // console.log(userList)
      this.userList = userList.content
    }
  }
}
</script>

<style scoped>

</style>
