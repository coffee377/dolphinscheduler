export interface ListReq {
  ipSegment?: string
  pageNum?: number
  pageSize?: number
  userId?: string
}
export interface addReq {
  id?: string
  ipSegment: string
  ipType: number
  userId?: string
}
export interface Record extends addReq {
  createTime: string
  updateTime: string
}
export interface ListRes {
  data: Record[]
  size: number
  pages: number
  total: number
}
export interface ListResData {
  data: ListRes
  status: number
}
