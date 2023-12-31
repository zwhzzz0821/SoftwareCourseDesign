import request from '@/utils/request'

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
  }).then(resp => {
    console.log(resp)
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
