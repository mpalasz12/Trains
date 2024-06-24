import React, { useEffect, useState } from "react";
import './Statistics.css';
import axios from "axios";

function Statistics() {

	const data = [
		{ train_id: 1, line_id: 101, locomotive_id: 201, curr_linestop: 1 },
		{ train_id: 2, line_id: 102, locomotive_id: 202, curr_linestop: 3 },
		{ train_id: 3, line_id: 103, locomotive_id: 203, curr_linestop: 2 },
	];

	const [stations, setStations] = useState([]);
	const [lines, setLines] = useState([]);
	const [mailes, setMailes] = useState([]);
	const [ticketCounts, setTicketCounts] = useState([]);

	const handleFetchTickets = async () => {
		const newTicketCounts = [];
		for (const mail of mailes) {
			try {
				const response = await axios.get('http://localhost:8080/data/get_ticket_count_by_mail', {
					params: { mail: mail }
				});
				const tickets = response.data;
				newTicketCounts.push({mail: mail, count: tickets});
				console.log(`Tickets for mail ${mail}:`, tickets);
			} catch (error) {
				console.error(`Error fetching tickets for mail ${mail}:`, error);
			}
		}
		setTicketCounts(newTicketCounts);
	};

	useEffect(() => {
		const fetchData = async () => {
			try {
				const responseStations = await axios.get('http://localhost:8080/data/all_stations', null, {});
				console.log("All cities, response:", responseStations.data);
				setStations(responseStations.data);
			} catch (error) {
				console.error("Error getting cities:", error);
			}
			try {
				const responseLines = await axios.get('http://localhost:8080/data/get_all_lines', null, {});
				console.log("All lines, response:", responseLines.data);
				setLines(responseLines.data);
			} catch (error) {
				console.error("Error getting all lines:", error);
			}
			try {
				const responseMailes = await axios.get('http://localhost:8080/data/get_all_mails', null, {});
				console.log("All mailes, response:", responseMailes.data);
				setMailes(responseMailes.data);
			} catch (error) {
				console.error("Error getting all mailes:", error);
			}
		};

		fetchData();
	}, []);

	useEffect(() => {
		if (mailes.length > 0) {
			handleFetchTickets();
		}
	}, [mailes]);

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
			<h2>Stations</h2>
			<table className="cities">
				<thead>
				<tr>
					<th>Station Names</th>
				</tr>
				</thead>
				<tbody>
				{stations.map((station) => (
					<tr key={station.station_id}>
						<td>{station.name}</td>
					</tr>
				))}
				</tbody>
			</table>
			<h2>Lines</h2>
			<table className="lines">
				<thead>
				<tr>
					<th>Start Station</th>
					<th>End Station</th>
				</tr>
				</thead>
				<tbody>
				{lines.map((line) => {
					const stations = line.name.split('-');
					return (
						<tr key={line.line_id}>
							<td>{stations[0]}</td>
							<td>{stations[1]}</td>
						</tr>
					);
				})}
				</tbody>
			</table>
			<h2>Tickets by Mail</h2>
			<table className="tickets">
				<thead>
				<tr>
					<th>Mail</th>
					<th>Ticket Count</th>
				</tr>
				</thead>
				<tbody>
				{ticketCounts.map((item) => (
					<tr key={item.mail}>
						<td>{item.mail}</td>
						<td>{item.count}</td>
					</tr>
				))}
				</tbody>
			</table>
		</div>
	);
}


export default Statistics;