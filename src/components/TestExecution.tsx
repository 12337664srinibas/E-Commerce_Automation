import React, { useState } from 'react'
import { Play, Square, RotateCcw, Download, Terminal, FileText } from 'lucide-react'

const TestExecution = () => {
  const [isRunning, setIsRunning] = useState(false)
  const [executionLog, setExecutionLog] = useState<string[]>([
    '🚀 Test execution environment ready',
    '📊 Framework: Selenium WebDriver + Java + TestNG',
    '🎯 Target: https://automationteststore.com/',
    '⏰ Ready to execute automated test scenarios'
  ])

  const handleStartTests = () => {
    setIsRunning(true)
    const newLogs = [
      '🔧 Initializing WebDriver (Chrome)',
      '🌐 Navigating to automation test store...',
      '🔍 Detecting categories dynamically...',
      '📂 Found categories: Makeup, Skincare, Fragrance, Men, Hair Care, Books',
      '🎲 Randomly selected category: Skincare',
      '📊 Verifying minimum 3 products... ✅ Found 8 products',
      '🛍️ Selecting random products for cart addition...',
      '📦 Product 1: Skinsheen Bronzer Stick - $29.50',
      '📦 Product 2: Benefit Bella Bamba - $28.00',
      '🛒 Adding products to cart...',
      '✅ Cart verification: 2 items, Total: $57.50',
      '➡️ Proceeding to checkout workflow...',
      '📝 Filling guest checkout form...',
      '🧪 Performing negative validation testing...',
      '❌ Testing empty fields validation...',
      '❌ Testing invalid email format...',
      '❌ Testing password mismatch...',
      '📊 Generating comprehensive reports...',
      '📄 Exporting data to CSV files...',
      '🎉 All test scenarios completed successfully!'
    ]

    let logIndex = 0
    const interval = setInterval(() => {
      if (logIndex < newLogs.length) {
        setExecutionLog(prev => [...prev, newLogs[logIndex]])
        logIndex++
      } else {
        setIsRunning(false)
        clearInterval(interval)
      }
    }, 800)
  }

  const handleStopTests = () => {
    setIsRunning(false)
    setExecutionLog(prev => [...prev, '⏹️ Test execution stopped by user'])
  }

  const handleClearLog = () => {
    setExecutionLog([
      '🚀 Test execution environment ready',
      '📊 Framework: Selenium WebDriver + Java + TestNG',
      '🎯 Target: https://automationteststore.com/',
      '⏰ Ready to execute automated test scenarios'
    ])
  }

  return (
    <div className="space-y-8">
      {/* Execution Controls */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h2 className="text-xl font-bold text-gray-900">Test Execution Control</h2>
            <p className="text-gray-600">Run and monitor your automated test scenarios</p>
          </div>
          
          <div className="flex items-center space-x-3">
            {!isRunning ? (
              <button
                onClick={handleStartTests}
                className="btn-primary flex items-center space-x-2"
              >
                <Play className="h-4 w-4" />
                <span>Start Tests</span>
              </button>
            ) : (
              <button
                onClick={handleStopTests}
                className="bg-red-600 hover:bg-red-700 text-white font-medium px-6 py-3 rounded-lg transition-all duration-200 flex items-center space-x-2"
              >
                <Square className="h-4 w-4" />
                <span>Stop Tests</span>
              </button>
            )}
            
            <button
              onClick={handleClearLog}
              className="btn-secondary flex items-center space-x-2"
            >
              <RotateCcw className="h-4 w-4" />
              <span>Clear Log</span>
            </button>
          </div>
        </div>

        {/* Execution Status */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
          <div className="bg-gray-50 rounded-lg p-4 text-center">
            <div className="text-2xl font-bold text-gray-900 mb-1">
              {isRunning ? '🔄' : '⏸️'}
            </div>
            <div className="text-sm text-gray-600">
              {isRunning ? 'Running' : 'Ready'}
            </div>
          </div>
          
          <div className="bg-gray-50 rounded-lg p-4 text-center">
            <div className="text-2xl font-bold text-aerospace-600 mb-1">5</div>
            <div className="text-sm text-gray-600">Test Scenarios</div>
          </div>
          
          <div className="bg-gray-50 rounded-lg p-4 text-center">
            <div className="text-2xl font-bold text-green-600 mb-1">
              {executionLog.filter(log => log.includes('✅')).length}
            </div>
            <div className="text-sm text-gray-600">Completed Steps</div>
          </div>
        </div>
      </div>

      {/* Execution Log */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200">
        <div className="flex items-center justify-between p-6 border-b border-gray-200">
          <div className="flex items-center space-x-2">
            <Terminal className="h-5 w-5 text-gray-600" />
            <h3 className="text-lg font-semibold text-gray-900">Execution Log</h3>
          </div>
          
          <div className="flex items-center space-x-2">
            <div className={`w-2 h-2 rounded-full ${isRunning ? 'bg-green-400 animate-pulse' : 'bg-gray-400'}`}></div>
            <span className="text-sm text-gray-600">
              {isRunning ? 'Live' : 'Idle'}
            </span>
          </div>
        </div>
        
        <div className="p-6">
          <div className="bg-gray-900 rounded-lg p-4 font-mono text-sm text-green-400 h-96 overflow-y-auto">
            {executionLog.map((log, index) => (
              <div key={index} className="mb-1 flex items-start space-x-2">
                <span className="text-gray-500 text-xs mt-0.5">
                  {String(index + 1).padStart(3, '0')}
                </span>
                <span className={`${
                  log.includes('✅') ? 'text-green-400' :
                  log.includes('❌') ? 'text-red-400' :
                  log.includes('🔍') || log.includes('🔧') ? 'text-yellow-400' :
                  log.includes('📦') || log.includes('🛒') ? 'text-blue-400' :
                  'text-green-400'
                }`}>
                  {log}
                </span>
              </div>
            ))}
            {isRunning && (
              <div className="flex items-center space-x-2 text-yellow-400">
                <span className="text-gray-500 text-xs">
                  {String(executionLog.length + 1).padStart(3, '0')}
                </span>
                <span className="animate-pulse">⏳ Executing test scenario...</span>
              </div>
            )}
          </div>
        </div>
      </div>

      {/* Quick Actions */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <h3 className="text-lg font-semibold text-gray-900 mb-4">Quick Actions</h3>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          <button className="flex items-center justify-center space-x-2 p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <FileText className="h-5 w-5 text-gray-600" />
            <span className="text-gray-700">View Test Reports</span>
          </button>
          
          <button className="flex items-center justify-center space-x-2 p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <Download className="h-5 w-5 text-gray-600" />
            <span className="text-gray-700">Download CSV Data</span>
          </button>
          
          <button className="flex items-center justify-center space-x-2 p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <Terminal className="h-5 w-5 text-gray-600" />
            <span className="text-gray-700">Open Terminal</span>
          </button>
        </div>
      </div>
    </div>
  )
}

export default TestExecution