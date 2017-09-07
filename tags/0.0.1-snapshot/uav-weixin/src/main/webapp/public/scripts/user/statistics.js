import React from 'react'
import flightBS64 from 'images/user/flight-times'
import applyBS64 from 'images/user/apply-times'
import outBS64 from 'images/user/out-times'
import hourBS64 from 'images/user/hour'
import { Cells, Cell, CellHeader, CellBody, CellFooter } from 'react-weui'
import "styles/user/stati.less"

export default class UserStatisticsApp extends React.Component{
	render(){
		let {
				flyTimes,
				flyHour,
				flyOvertime,
			} = this.props.match.params;
		return (
			<div id="user_stati_app">
				<Cells className = "ft">
					<Cell>
						<CellHeader>
							<dl>
								<dd><img src={ flightBS64 }/></dd>
								<dt>飞行次数</dt>
							</dl>
						</CellHeader>
						<CellBody></CellBody>
						<CellFooter><span className = "num">{ flyTimes || 0 }</span>次</CellFooter>
					</Cell>
				</Cells>
				<Cells className = "fh">
					<Cell>
						<CellHeader>
							<dl>
								<dd><img src={ hourBS64 }/></dd>
								<dt>飞行时数</dt>
							</dl>
						</CellHeader>
						<CellBody></CellBody>
						<CellFooter><span className = "num">{ flyHour || 0 }</span>小时</CellFooter>
					</Cell>
				</Cells>
				{/*<Cells className = "at">
					<Cell>
						<CellHeader>
							<dl>
								<dd><img src={ applyBS64 }/></dd>
								<dt>空域申请</dt>
							</dl>
						</CellHeader>
						<CellBody></CellBody>
						<CellFooter><span className = "num">0</span>次</CellFooter>
					</Cell>
				</Cells>*/}
				<Cells className = "ot">
					<Cell>
						<CellHeader>
							<dl>
								<dd><img src={ outBS64 }/></dd>
								<dt>违规次数</dt>
							</dl>
						</CellHeader>
						<CellBody></CellBody>
						<CellFooter><span className = "num">{ flyOvertime || 0 }</span>次</CellFooter>
					</Cell>
				</Cells>
			</div>
		)
	}
}