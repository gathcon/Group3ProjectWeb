
function graph(names, numbers, graphTitle, subTitle, xTitle, yTitle) {
        var options = {
            chart: {
                renderTo: 'container',
				type: 'bar'
            },
            title: {
                text: graphTitle
            },
            subtitle: {
                text: subTitle
            },
            xAxis: {
                categories: names,
                title: {
                    text: xTitle
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: yTitle,
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: yTitle
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true,
				enabled: false
				
				
				
            },
			exporting: { enabled: false},
            credits: {
                enabled: false
            },
            series: [ {
                name: yTitle,
                data: numbers
            }]
        };
		
		var chart = new Highcharts.Chart(options);
    }