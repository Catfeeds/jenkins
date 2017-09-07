import React from 'react'
import Tool from 'utils/tool'
import PageComponent from 'common/page-component'
import paging from 'common/paging'
import NoData from 'scripts/nodata'
import isEmpty from 'lodash/isEmpty'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxBody, MediaBoxDescription, Icon } from 'react-weui'
import "styles/notice/list.less"

export default class NoticeListApp extends PageComponent{
	constructor(props) {
		super(props);
		this.state = {
			dataSource : []
		};
	}
	componentDidMount(){
		this.fetchData(`${ config.ajaxPath }/announcements`, { page : 1, size : this.params.pageSize } )
	}
	filter( json ){
		return json.data
	}
	render(){
		let {
			dataSource
		} = this.state;
		return isEmpty( dataSource ) ? <NoData/> :(
			<div id= "notice_list_app">
				{
					Array.isArray( dataSource ) && dataSource.map( ( v, k ) => {
						return (
							<Panel key = { k } onClick = { () => Tool.to(`/notice/detail/${ v.id }`)}>
								<PanelHeader><Icon value = "message" size = "small"/><span>{ v.subTitle }</span></PanelHeader>
								<PanelBody>
									<MediaBox>
										<MediaBoxBody>{ v.title }</MediaBoxBody>
										<MediaBoxDescription>日期：{ v.createTime }</MediaBoxDescription>
									</MediaBox>
								</PanelBody>
							</Panel>
						)
					})
				}
				{/*<MoreBar loadMore = { this.fetchDataByPaging } paging = { this.params }/>*/}
			</div>
		)
	}
}