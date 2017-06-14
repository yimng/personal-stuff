#!/bin/bash

echo "Here"
unix2dos $0
chmod 755 $0

./$0

echo "There"
exit 0
