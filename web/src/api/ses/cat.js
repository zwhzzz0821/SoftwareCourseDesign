import request from '@/utils/request'

export function getCats(params) {
  return request({
    url: 'api/cat',
    method: 'get',
    params
  })
}

export function add(data) {
  return request({
    url: 'api/cat',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/cat/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/cat',
    method: 'put',
    data
  })
}

export default { add, edit, del }
