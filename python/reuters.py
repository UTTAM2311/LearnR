BASE_URL = 'http://www.reuters.com'
ARCHIVE_BASE = BASE_URL + '/news/archive/worldNews'

import sys
sys.dont_write_bytecode = True
from BeautifulSoup import BeautifulSoup
import requests
import json


def parse_urls(date):
    url = ARCHIVE_BASE + '?date=' + date
    content = BeautifulSoup(requests.get(url)._content)
    wrap = content.find('div', 'moduleBody').findAll('div', 'feature')
    links = ['%s%s' % (BASE_URL, x.find('h2').find('a').get('href')) for x in wrap]
    parsed_contents = []
    for (idx, elem) in enumerate(links[0:10]):
        content = requests.get(elem)._content
        soup = BeautifulSoup(content)
        brief = soup.find('span', 'focusParagraph')
        hwrap = soup.find('div', 'sectionColumns')
        title = hwrap.find('h1')
        title = title.text if title else ''
        description = brief.text if brief else ''
        data = {
            'title': title,
            # 'description': brief
        }
        parsed_contents.append({
            'idx': idx,
            # 'content': str(content),
            'data': data,
            'url': elem
        })        
        print "processed #%s" % idx

    with open('data.json', 'w') as jfile:
            jfile.write(str(parsed_contents))


if __name__ == '__main__':
    print parse_urls('08022013')
