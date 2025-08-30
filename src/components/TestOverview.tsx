import React from 'react'
import { CheckCircle, AlertCircle, Clock, FileText, Database, Bug } from 'lucide-react'

const TestOverview = () => {
  const testScenarios = [
    {
      id: 1,
      title: 'Homepage & Category Verification',
      description: 'Navigate to homepage, detect categories dynamically, and verify product availability',
      status: 'ready',
      features: ['Dynamic category detection', 'Product count validation', 'Navigation verification']
    },
    {
      id: 2,
      title: 'Product Selection & Cart Addition',
      description: 'Randomly select 2 products, capture details, and add to cart with validation',
      status: 'ready',
      features: ['Random product selection', 'Detail capture', 'Cart validation', 'Stock status check']
    },
    {
      id: 3,
      title: 'Cart & Checkout Workflow',
      description: 'Navigate to cart, verify items, proceed through checkout, and simulate registration',
      status: 'ready',
      features: ['Cart verification', 'Checkout process', 'Form automation', 'Guest checkout']
    },
    {
      id: 4,
      title: 'Negative Scenario Testing',
      description: 'Test registration validation with empty fields, invalid email, and mismatched passwords',
      status: 'ready',
      features: ['Empty field validation', 'Email format testing', 'Password mismatch', 'Error capture']
    },
    {
      id: 5,
      title: 'Report Generation & Data Export',
      description: 'Generate comprehensive test report with all captured data and statistics',
      status: 'ready',
      features: ['HTML reports', 'CSV export', 'Screenshot capture', 'Statistics generation']
    }
  ]

  const getStatusIcon = (status: string) => {
    switch (status) {
      case 'ready':
        return <CheckCircle className="h-5 w-5 text-green-500" />
      case 'running':
        return <Clock className="h-5 w-5 text-yellow-500" />
      case 'failed':
        return <AlertCircle className="h-5 w-5 text-red-500" />
      default:
        return <CheckCircle className="h-5 w-5 text-gray-400" />
    }
  }

  const getStatusBadge = (status: string) => {
    const baseClasses = "status-badge"
    switch (status) {
      case 'ready':
        return `${baseClasses} status-pass`
      case 'running':
        return `${baseClasses} status-running`
      case 'failed':
        return `${baseClasses} status-fail`
      default:
        return `${baseClasses} status-pending`
    }
  }

  return (
    <div className="space-y-8">
      {/* Project Summary */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-8">
        <div className="flex items-start justify-between">
          <div>
            <h2 className="text-2xl font-bold text-gray-900 mb-2">
              🚀 E-Commerce Automation Testing Assignment
            </h2>
            <p className="text-gray-600 mb-4">
              Comprehensive automated testing framework for dynamic e-commerce workflows using Selenium WebDriver + Java + TestNG
            </p>
            <div className="flex items-center space-x-6 text-sm text-gray-500">
              <div className="flex items-center space-x-2">
                <Database className="h-4 w-4" />
                <span>Target: automationteststore.com</span>
              </div>
              <div className="flex items-center space-x-2">
                <FileText className="h-4 w-4" />
                <span>Pattern: Page Object Model</span>
              </div>
              <div className="flex items-center space-x-2">
                <Bug className="h-4 w-4" />
                <span>Framework: Selenium + TestNG</span>
              </div>
            </div>
          </div>
          <div className="text-right">
            <div className="text-3xl font-bold text-aerospace-600">5</div>
            <div className="text-sm text-gray-500">Test Scenarios</div>
          </div>
        </div>
      </div>

      {/* Test Scenarios Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {testScenarios.map((scenario) => (
          <div key={scenario.id} className="bg-white rounded-xl shadow-sm border border-gray-200 p-6 card-hover">
            <div className="flex items-start justify-between mb-4">
              <div className="flex items-center space-x-3">
                {getStatusIcon(scenario.status)}
                <h3 className="text-lg font-semibold text-gray-900">
                  {scenario.title}
                </h3>
              </div>
              <span className={getStatusBadge(scenario.status)}>
                {scenario.status.toUpperCase()}
              </span>
            </div>
            
            <p className="text-gray-600 mb-4 leading-relaxed">
              {scenario.description}
            </p>
            
            <div className="space-y-2">
              <h4 className="text-sm font-medium text-gray-700">Key Features:</h4>
              <ul className="space-y-1">
                {scenario.features.map((feature, index) => (
                  <li key={index} className="flex items-center space-x-2 text-sm text-gray-600">
                    <div className="w-1.5 h-1.5 bg-aerospace-400 rounded-full"></div>
                    <span>{feature}</span>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        ))}
      </div>

      {/* Technical Specifications */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-8">
        <h3 className="text-xl font-bold text-gray-900 mb-6">🔧 Technical Specifications</h3>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          <div className="text-center p-4 bg-gray-50 rounded-lg">
            <div className="text-2xl font-bold text-aerospace-600 mb-2">Java 11+</div>
            <div className="text-sm text-gray-600">Programming Language</div>
          </div>
          
          <div className="text-center p-4 bg-gray-50 rounded-lg">
            <div className="text-2xl font-bold text-aerospace-600 mb-2">Selenium 4.15</div>
            <div className="text-sm text-gray-600">WebDriver Framework</div>
          </div>
          
          <div className="text-center p-4 bg-gray-50 rounded-lg">
            <div className="text-2xl font-bold text-aerospace-600 mb-2">TestNG 7.8</div>
            <div className="text-sm text-gray-600">Test Framework</div>
          </div>
          
          <div className="text-center p-4 bg-gray-50 rounded-lg">
            <div className="text-2xl font-bold text-aerospace-600 mb-2">POM</div>
            <div className="text-sm text-gray-600">Design Pattern</div>
          </div>
        </div>
      </div>

      {/* Assignment Compliance */}
      <div className="bg-gradient-to-r from-aerospace-50 to-blue-50 rounded-xl border border-aerospace-200 p-8">
        <h3 className="text-xl font-bold text-gray-900 mb-6">✅ Assignment Compliance Status</h3>
        
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="space-y-3">
            <h4 className="font-semibold text-gray-800">Core Requirements</h4>
            <div className="space-y-2">
              {[
                'Homepage Navigation',
                'Dynamic Category Detection', 
                'Random Product Selection',
                'Cart Workflow Verification'
              ].map((requirement, index) => (
                <div key={index} className="flex items-center space-x-2">
                  <CheckCircle className="h-4 w-4 text-green-500" />
                  <span className="text-sm text-gray-700">{requirement}</span>
                </div>
              ))}
            </div>
          </div>
          
          <div className="space-y-3">
            <h4 className="font-semibold text-gray-800">Advanced Features</h4>
            <div className="space-y-2">
              {[
                'Negative Testing Scenarios',
                'Professional Reporting',
                'CSV Data Export',
                'Screenshot Documentation'
              ].map((feature, index) => (
                <div key={index} className="flex items-center space-x-2">
                  <CheckCircle className="h-4 w-4 text-green-500" />
                  <span className="text-sm text-gray-700">{feature}</span>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default TestOverview