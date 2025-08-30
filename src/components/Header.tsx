import React from 'react'
import { Rocket, Shield, Target } from 'lucide-react'

const Header = () => {
  return (
    <header className="bg-white shadow-sm border-b border-gray-200">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center space-x-4">
            <div className="flex items-center space-x-3">
              <div className="relative">
                <Rocket className="h-8 w-8 text-aerospace-600" />
                <div className="absolute -top-1 -right-1 w-3 h-3 bg-aerospace-400 rounded-full animate-pulse"></div>
              </div>
              <div>
                <h1 className="text-xl font-bold text-gray-900">Ziegler Aerospace</h1>
                <p className="text-sm text-gray-500">E-Commerce Automation Testing</p>
              </div>
            </div>
          </div>
          
          <div className="flex items-center space-x-6">
            <div className="flex items-center space-x-2 text-sm text-gray-600">
              <Shield className="h-4 w-4 text-green-500" />
              <span>Selenium + Java + TestNG</span>
            </div>
            <div className="flex items-center space-x-2 text-sm text-gray-600">
              <Target className="h-4 w-4 text-aerospace-500" />
              <span>automationteststore.com</span>
            </div>
          </div>
        </div>
      </div>
    </header>
  )
}

export default Header