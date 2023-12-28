<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <el-row>
        <el-col :span="18">
          <div class="grid-content bg-purple">
            <div v-if="crud.props.searchToggle">
              <!-- 搜索 -->
              <label class="el-form-item-label">小吃名</label>
              <el-input v-model="query.title" clearable placeholder="小吃名" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
              <rrOperation :crud="crud" />
            </div>
            <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
            <crudOperation :permission="permission" />
            <!--表单组件-->
            <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
              <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
                <el-form-item label="小吃名" prop="title">
                  <el-input v-model="form.title" style="width: 370px;" />
                </el-form-item>
                <el-form-item label="单价">
                  <el-input v-model="form.unitPrice" style="width: 370px;" />
                </el-form-item>
                <el-form-item label="数量">
                  <el-input v-model="form.quantity" style="width: 370px;" />
                </el-form-item>
                <el-form-item label="描述">
                  <el-input v-model="form.description" :rows="3" type="textarea" style="width: 370px;" />
                </el-form-item>
                <el-form-item label="商品种类">
                  <el-select v-model="form.catId" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.catId"
                      :label="item.kind"
                      :value="item.catId"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="店铺">
                  <el-select v-model="form.shopId" placeholder="请选择">
                    <el-option
                      v-for="item in shopList"
                      :key="item.shopId"
                      :label="item.title"
                      :value="item.shopId"
                    />
                  </el-select>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button type="text" @click="crud.cancelCU">取消</el-button>
                <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
              </div>
            </el-dialog>
            <!--表格渲染-->
            <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
              <el-table-column type="selection" width="55" />
              <el-table-column prop="productId" label="小吃ID" />
              <el-table-column prop="title" label="小吃名" />
              <el-table-column prop="unitPrice" label="单价" />
              <el-table-column prop="quantity" label="数量" />
              <el-table-column prop="description" label="描述" />
              <el-table-column :show-overflow-tooltip="true" prop="cat" label="商品种类">
                <template slot-scope="scope">
                  <div>{{ scope.row.cat.kind }}</div>
                </template>
              </el-table-column>
                     <el-table-column prop="cats" label="商品种类">
                       <template slot-scope="scope">
                         <div>{{ scope.row.cats.title }}</div>
                       </template>
                     </el-table-column>
              <el-table-column v-if="checkPer(['admin','product:edit','product:del'])" label="操作" width="150px" align="center">
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
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple-light">
            <el-button @click="refreshProduct(null)">
              显示全部商品
            </el-button>
            <el-divider />
            <div v-for="shop in shopList" :key="shop.shopId" style="margin-left: 10px">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>{{ shop.title }}</span>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="refreshProduct(shop.shopId)">查看商品</el-button>
                </div>
                <div class="text item">
                  {{ shop.description }}
                </div>
              </el-card>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import crudProduct from '@/api/product'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import { getCats } from '@/api/ses/cat'
import { getShop } from '@/api/ses/shop'
const defaultForm = { productId: null, title: null, unitPrice: null, quantity: null, description: null, shopId: null, cat: { cat_id: null }}
export default {
  name: 'Product',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '商品', url: 'api/product', idField: 'productId', sort: 'productId,desc', crudMethod: { ...crudProduct }})
  },
  data() {
    return {
      options: null,
      shopList: [],
      permission: {
        add: ['admin', 'product:add'],
        edit: ['admin', 'product:edit'],
        del: ['admin', 'product:del']
      },
      rules: {
        title: [
          { required: true, message: '小吃名不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'title', display_name: '小吃名' }
      ]
    }
  },
  created() {
    this.getCat()
    this.getAllShop()
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      this.getCat()
      return true
    },
    async getCat() {
      const catList = await getCats()
      console.log(catList)
      this.options = catList.content
    },
    async getAllShop() {
      const catList = await getShop()
      console.log(11111, catList)
      console.log(11111, catList)
      this.shopList = catList.content
    },
    async refreshProduct(shopId) {
      this.query.shopId = shopId
      this.crud.toQuery()
    }
  }
}
</script>

<style scoped>

</style>
