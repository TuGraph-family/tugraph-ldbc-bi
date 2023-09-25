#!/usr/bin/python3
from datetime import datetime, timezone, date


def dateTime_to_unixTimeStampMillis(datetimeStr:str):
    return int(datetime
    .strptime(datetimeStr[:-6],"%Y-%m-%dT%H:%M:%S.%f")
    .replace(tzinfo=timezone.utc)
    .timestamp() * 1000)

def datestr_to_unixTimeStampMillis(dateStr:str):
    return int(datetime
    .strptime(dateStr,"%Y-%m-%d")
    .replace(tzinfo=timezone.utc)
    .timestamp() * 1000)

def date_to_unixTimeStampMillis(dateValue:date):
    dt = datetime(dateValue.year, dateValue.month, dateValue.day)
    return int(dt.replace(tzinfo=timezone.utc)
    .timestamp() * 1000)

def unixTimeStampMillis_to_dateTime(timestamp:int):
    return datetime.utcfromtimestamp(timestamp // 1000)\
        .replace(microsecond=timestamp % 1000 * 1000)\
        .strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "+00:00"

def read_query(path:str):
    return open(path).read()