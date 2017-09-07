import React from 'react'
import Tool from 'utils/tool'
import PageComponent from 'common/page-component'
import paging from 'common/paging'
import MoreBar from 'common/more-bar'
import NoData from 'scripts/nodata'
import isEmpty from 'lodash/isEmpty'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxHeader, MediaBoxBody, MediaBoxDescription, Flex, FlexItem, Cells, Cell, CellBody, CellFooter } from 'react-weui'
import "styles/news/list.less"

export default class NewsListApp extends PageComponent{
	constructor(props) {
		super(props);
		this.state = {
			dataSource : []
		};
	}
	componentDidMount(){
		this.fetchData(`${ config.ajaxPath }/portal/menu/cate5/content`, { page : 1, size : paging.pageSize } )
	}
	render(){
		let {
			dataSource
		} = this.state;
		return isEmpty( dataSource ) ? <NoData/> :(
			<div id= "news_app">
				{
					Array.isArray( dataSource ) && dataSource.map( ( v, k ) => {
						return (
							<Cells key = { k }>
								<Cell access onClick = { () => Tool.to(`/news/detail/${ v.id }`) }>
									<CellBody>{ v.title }</CellBody>
									<CellFooter>&nbsp;</CellFooter>
								</Cell>
								<Cell>
									<CellBody>
										<Flex>
											<FlexItem style = {{ textAlign : "left" }}>
												日期：{ v.createTime }
											</FlexItem>
											<FlexItem style = {{ textAlign : "right" }}>
												{ v.checkNumber }次浏览
											</FlexItem>
										</Flex>
									</CellBody>
								</Cell>
							</Cells>
						)
					})
				}
				<MoreBar loadMore = { this.fetchDataByPaging } paging = { this.params }/>
			</div>
		)
	}
}