import { axios } from '@/service/request'
import { TaskListReq } from '@/service/modules/log/types'
export function queryTaskListPaging(params: TaskListReq): any {
  return axios({
    url: '/platform/taskInstance/page',
    method: 'get',
    params
  })
}
