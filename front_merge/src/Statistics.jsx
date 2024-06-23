import React from "react";
import './Statistics.css';

function Statistics() {

	const data = [
		{ train_id: 1, line_id: 101, locomotive_id: 201, curr_linestop: 1 },
        { train_id: 2, line_id: 102, locomotive_id: 202, curr_linestop: 3 },
        { train_id: 3, line_id: 103, locomotive_id: 203, curr_linestop: 2 },
	  ];

	return (
		<div className="table-container">
      		<table className="table">
        		<thead>
          			<tr>
            			<th>Train ID</th>
            			<th>Line ID</th>
            			<th>Locomotive ID</th>
            			<th>Current Line Stop</th>
          			</tr>
        		</thead>
        		<tbody>
          			{data.map((item) => (
            			<tr key={item.train_id}>
              				<td>{item.train_id}</td>
              				<td>{item.line_id}</td>
              				<td>{item.locomotive_id}</td>
              				<td>{item.curr_linestop}</td>
            			</tr>
          			))}
        		</tbody>
      		</table>
    	</div>
	);
}


export default Statistics;